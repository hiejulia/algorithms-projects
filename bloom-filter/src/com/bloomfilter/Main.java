package com.bloomfilter;

import java.util.List;

/**
 * POC how Bloom Filter construct
 */
public class Main {

    public static void main(String[] args) {
	// write your code here

        Funnel<Friend> personFunnel = new Funnel<Friend>() {
            @Override
            public void funnel(Friend person, PrimitiveSink into) {
                into
                        .putInt(person.id)
                        .putString(person.firstName, Charsets.UTF_8)
                        .putString(person.lastName, Charsets.UTF_8)
                        .putInt(birthYear);
            }
        };



        List<Friend> friendsList = {Person1, Person2, ...};
        BloomFilter<Friend> friends = BloomFilter.create(personFunnel, 500, 0.01);
        for(Friend friend : friendsList) {
            friends.put(friend);
        }

        // much later, use the Bloom filter
        Friend dude = new Friend(100, "Alex", "Smith", 1967);
        if (friends.mightContain(dude)) {
            // the probability that dude reached this place
            // if he isn't a friend is 1% (0.01); we might,
            // for example, start asynchronously loading things
            // for dude while we do a more expensive exact check
        }



    }
}
