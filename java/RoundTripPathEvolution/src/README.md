# RoundTripPathEvolution

This uses evolutionary algorithms to evolve the shortest path for locations in luxembourg 
(data aquired from here:http://www.math.uwaterloo.ca/tsp/world/countries.html)

## Chromosome

Esentially each Chromosome encodes locations, there is a list of locations the order of each
location in the list denotes the path i.e. [locA, locB, locC] === locA -> locB -> locC.

## ChromosomeBuilder

A builder design pattern is employed to encapsulate population construction (a list of chromosomes).

## Crossover

We couldn't simply snip each parent and select randomly from the snips, as it's possible that
locations are lossed in the process as order maters. So I use order one crossover as a preliminary
before optimisation.

