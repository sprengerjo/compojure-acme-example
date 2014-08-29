(ns acme.gol)

(defn life? [cell n cells]
  (if (or (and (cells cell) (= n 2)) (= n 3)) true false))

(defn neighbors [[x y]]
 (set (for [dx [-1 0 1] dy (if (= dx 0) [-1 1] [-1 0 1])]
        [(+ x dx) (+ y dy)])))

(defn step [cells]
  (set (for [[cell n] (frequencies (mapcat neighbors cells)) 
             :when (life? cell n cells)]
         cell)))