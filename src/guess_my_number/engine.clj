(ns guess-my-number.engine)

(def small 1)

(def big 100)

(defn guess-my-number []
  (quot (+ small big) 2))

(defn smaller []
  (def big (- (guess-my-number) 1))
  (guess-my-number))

(defn bigger []
  (def small (+ (guess-my-number) 1))
  (guess-my-number))

(defn start-over []
  (def small 1)
  (def big 100)
  (guess-my-number))

(comment
  (guess-my-number)
  (bigger)
  (smaller)
  (start-over)
  :rcf)