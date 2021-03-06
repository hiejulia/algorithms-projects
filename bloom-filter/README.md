# bloom filter 
<a href="https://imgur.com/iFWM61z"><img src="https://i.imgur.com/iFWM61z.png" title="source: imgur.com" /></a>

- test if sets contains element 
- fit in memory 
- Given a big set S = {x1, x2, ..., xn}, the Bloom filter is a probabilistic, fast, and space-efficient cache builder; it basically approximates the set membership operation:

Is x ∊ S?
Two possible errors are:

False positives: x ∉ S, but the answer is x ∈ S
False negatives: x ∈ S, but the answer is x ∉ S

- probability of falsse positive : 
<a href="https://imgur.com/LUhjghW"><img src="https://i.imgur.com/LUhjghW.png" title="source: imgur.com" /></a>

The following mathematical formulas can be used to shape the optimal Bloom filter:

Number of items in the filter (can be estimated based on m, k, and p):
n = ceil(m / (-k / log(1 - exp(log(p) / k))));

Probability of false positives, a fraction between 0 and 1, or a number indicating 1-in-p:
p = pow(1 - exp(-k / (m / n)), k);

Number of bits in the filter (or size in terms of KB, KiB, MB, Mb, GiB, and so on):
m = ceil((n * log(p)) / log(1 / pow(2, log(2))));

Number of hash functions (can be estimated based on m and n):
k = round((m / n) * log(2));


- array of bits 
- 

- pairwise independent 
- uniformly distributed hash function 
- add element to BL 
    - hash function : SHA1, MD5, Murmur 
    - set bits in the bit array 

- example : 
<a href="https://imgur.com/U0plEVE"><img src="https://i.imgur.com/U0plEVE.png" title="source: imgur.com" /></a>
`
private BitSet bitset; // the array of bits
private static final Charset CHARSET = StandardCharsets.UTF_8;
...
public void add(T element) {

  add(element.toString().getBytes(CHARSET));
}

public void add(byte[] bytes) {

  int[] hashes = hash(bytes, numberOfHashFunctions);

  for (int hash: hashes) {
    bitset.set(Math.abs(hash % bitSetSize), true);
  }

  numberOfAddedElements++;
}
`
<a href="https://imgur.com/ag1eQ6W"><img src="https://i.imgur.com/ag1eQ6W.png" title="source: imgur.com" /></a>

## BL in Guava lib 

<a href="https://imgur.com/VaZpfBz"><img src="https://i.imgur.com/VaZpfBz.png" title="source: imgur.com" /></a>

## BL in Map Reduce 
- build BL out of relation and test value for membership using built BL 
- Reduce-side join optimization : BL on map tasks 
    - BL construct 
    - Mappers 
    - Reduce side join 

## practical applications / software
- cassandra use bloom filter
- Guava Library 
- map reduce framework 
- routing table lookup 
- online traffic measurement 
- peer to peer system 
- cooperative caching 
- firewall design 
- intrusion detection 
- bioinformatics 
- database query processing 
- stream computing 
