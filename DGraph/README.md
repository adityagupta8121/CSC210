% Input File: big11.mtx - Run 1
heuristic: cost = 3.3969307170000005, 3 milliseconds
mine: cost = 3.5544496719999996, 2 milliseconds
backtrack: cost = 1.3566775349999998, 131 milliseconds

% Input File: big11.mtx - Run 2
heuristic: cost = 3.3969307170000005, 3 milliseconds
mine: cost = 3.5544496719999996, 1 milliseconds
backtrack: cost = 1.3566775349999998, 129 milliseconds

% Input File: big11.mtx - Run 3
heuristic: cost = 3.3969307170000005, 4 milliseconds
mine: cost = 3.5544496719999996, 1 milliseconds
backtrack: cost = 1.3566775349999998, 103 milliseconds

% Input File: az.mtx - Run 1
heuristic: cost = 604.3, 2 milliseconds
mine: cost = 604.3, 0 milliseconds
backtrack: cost = 604.3, 1 milliseconds

% Input File: az.mtx - Run 2
heuristic: cost = 604.3, 2 milliseconds
mine: cost = 604.3, 0 milliseconds
backtrack: cost = 604.3, 1 milliseconds

% Input File: az.mtx - Run 3
heuristic: cost = 604.3, 2 milliseconds
mine: cost = 604.3, 1 milliseconds
backtrack: cost = 604.3, 1 milliseconds

% Input File: example.mtx - Run 1
heuristic: cost = 10.0, 2 milliseconds
mine: cost = 10.0, 0 milliseconds
backtrack: cost = 10.0, 0 milliseconds

% Input File: example.mtx - Run 2
heuristic: cost = 10.0, 2 milliseconds
mine: cost = 10.0, 0 milliseconds
backtrack: cost = 10.0, 0 milliseconds

% Input File: example.mtx - Run 3
heuristic: cost = 10.0, 2 milliseconds
mine: cost = 10.0, 0 milliseconds
backtrack: cost = 10.0, 0 milliseconds

% Input File: tinyRW.mtx - Run 1
heuristic: cost = 4.7, 2 milliseconds
mine: cost = 4.7, 0 milliseconds
backtrack: cost = 4.7, 0 milliseconds

% Input File: tinyRW.mtx - Run 2
heuristic: cost = 4.7, 1 milliseconds
mine: cost = 4.7, 0 milliseconds
backtrack: cost = 4.7, 0 milliseconds

% Input File: tinyRW.mtx - Run 3
heuristic: cost = 4.7, 2 milliseconds
mine: cost = 4.7, 0 milliseconds
backtrack: cost = 4.7, 0 milliseconds

%%%%%%%%%%%%%%%%%%
Conclusion
%%%%%%%%%%%%%%%%%%
The heuristic approach is quicker as it goes through each city and their routes once
while the backtrack approach goes through every available route and so returns route
with the minimum cost.
My approach has a confition where after adding a city if soFar is exceeding the cost of
minimum route then it doesn't process beyond which reduces the time by a lot.
%%%%%%%%%%%%%%%%%%