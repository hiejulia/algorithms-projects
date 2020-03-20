[org.clojure/clojure "1.7.0-RC1"]


(ns recipe24.core
  (:require [clojure.java.io :refer :all]
            [clojure.core.async :refer [chan
                                        go
                                        >!
                                        <!
                                        >!!
                                        <!!]]))


(defn source-nat
  [pub-ip
   tcp-frame]
  ;; Change source ip into the public IP Interface
  (assoc tcp-frame :source-ip pub-ip))

(def public-interface "1.2.3.4")
;; This is our public interface IP

(def tr-source-nat (map (partial source-nat
                                 public-interface)))
;; A transducer transforming tcp frames in such a way
;; That Source IP contains the public interface's.


(defn accepted?
  [accept-rules
   tcp-frame]
  (not
   (nil?
    (some #{tcp-frame} accept-rules))))
;; Verify if this TCP frame exists within
;; the accept-rules ACL

(def sample-accept-rules [{:source-ip "4.5.3.8" :dest-ip "4.4.3.5" :dest-port 80}
                          {:source-ip "4.5.3.9" :dest-ip "4.4.3.4" :dest-port 80}])

(def tr-filter-rules (filter (partial accepted?
                                      sample-accept-rules)))
;; A transducer dropping tcp frames not present
;; in our sample ACL

(def firewall(comp
              tr-filter-rules
              tr-source-nat))


(def sample-frames [{:source-ip "1.1.1.1" :dest-ip "2.3.2.2" :dest-port 10}
 {:source-ip "2.2.2.2" :dest-ip "4.3.4.1" :dest-port 20}
 {:source-ip "4.5.3.8" :dest-ip "4.4.3.5" :dest-port 30}
 {:source-ip "4.5.3.9" :dest-ip "4.4.3.4" :dest-port 80}])

(transduce firewall
           conj
           sample-frames)
;; => [{:source-ip "1.2.3.4", :dest-ip "4.4.3.4", :dest-port 80}]


(def source-hosts [ "4.5.3.8" "4.5.3.9"  "1.1.1.1" "2.2.2.2" ])
(def dest-hosts [ "4.4.3.5" "4.4.3.4"  "2.3.2.2" "4.3.4.1" ])
(def ports [ 80])

(defn get-random-elt
  [v]
    (get v (rand-int (dec (count  v)))))

(defn random-frame []
  {:source-ip (get-random-elt source-hosts)
   :dest-ip (get-random-elt dest-hosts)
   :dest-port (get-random-elt ports)})



(def from-net (chan 10
                                firewall))

(defn gen-frames!
  []
  (go
    (while true
      (let [fr (random-frame)]
        (>! from-net fr)
        (Thread/sleep 1000)))))




(defn show-fw-output!
  []
  (while true
    (println "accepted & NAT'ted : "
             (<!! from-net))))
