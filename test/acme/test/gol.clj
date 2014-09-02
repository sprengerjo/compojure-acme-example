(ns acme.test.gol
   (:use midje.sweet
         acme.gol))

(facts 
  "cell without neighbors dies"
  (life? [1 1] 0 #{}) => falsey
  "cell with 1 neighbor dies"
  (life? [1 1] 1 #{}) => falsey
  "cell with 2 neighbor lifes"
  (life? [1 1] 2 #{[1 1]}) => truthy
  "deead cell with 2 neighbor do not life"
  (life? [1 1] 2 #{}) => falsey
  "cell with 3 neighbor lifes"
  (life? [1 1] 3 #{}) => truthy
  "cell with 4 or more neighbors  dies"
  (life? [1 1] 5 #{}) => falsey
  )

(facts 
  "neighors of 1 1"
  (neighbors [1 1]) => #{[0 0] [0 1] [0 2] [1 0] [1 2] [2 0] [2 1] [2 2]}
  "get next generation of blinker pattern"
  (step #{[1 0] [1 1] [1 2]}) => #{[2 1] [1 1] [0 1]}
  "get next 5 generations of blinker pattern"
  (take 5 (iterate step #{[1 0] [1 1] [1 2]})) =>  [#{[1 0] [1 1] [1 2]} #{[2 1] [1 1] [0 1]} #{[1 0] [1 1] [1 2]} #{[2 1] [1 1] [0 1]} #{[1 0] [1 1] [1 2]}]
  )