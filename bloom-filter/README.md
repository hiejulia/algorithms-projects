# bloom filter 
- test if sets contains element 
- fit in memory 
- Given a big set S = {x1, x2, ..., xn}, the Bloom filter is a probabilistic, fast, and space-efficient cache builder; it basically approximates the set membership operation:

Is x ∊ S?
Two possible errors are:

False positives: x ∉ S, but the answer is x ∈ S
False negatives: x ∈ S, but the answer is x ∉ S

- probability of falsse positive : 




- pairwise independent 
- uniformly distributed hash function 
- add element to BL 
    - hash function : SHA1, MD5, Murmur 
    - set bits in the bit array 

## BL in Guava lib 
- 


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
