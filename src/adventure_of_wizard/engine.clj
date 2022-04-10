(ns adventure-of-wizard.engine)

(def nodes {:living-room '[you are in the living-room. a wizard is snoring loudly on the couch.],
            :garden      '[you are in a beautiful garden. there is a well in front of you.],
            :attic       '[you are in the attic. there is a giant welding torch in the corner.]})

(defn describe-location [location nodes]
  (location nodes))

(def edges {:living-room '[[:garden :west door] [:attic :upstairs ladder]],
            :garden      '[[:living-room :east door]],
            :attic       '[[:living-room :downstairs ladder]]})

(defn describe-path [edge]
  (let [[_ direction way] edge]
    `[there is a ~way going ~direction from here.]))

(defn describe-paths [location edges]
  (->> (location edges)
       (map describe-path)
       (apply concat)))

(defn find-next-edge-at [location direction edges]
  (->> (location edges)
       (filter #(= direction (second %)))
       (apply concat)))

(def objects [:whiskey :bucket :frog :chain])

(def object-locations {:whiskey :living-room,
                       :bucket  :living-room,
                       :chain   :garden,
                       :frog    :garden})

(defn objects-at [loc objs obj-locs]
  (->> objs
       (filter #(= loc (% obj-locs)))))

(defn describe-object [obj]
  `[you see a ~obj on the floor.])

(defn describe-objects [loc objs obj-locs]
  (->> (objects-at loc objs obj-locs)
       (map describe-object)
       (apply concat)))

(def location :living-room)

; user command
(defn look []
  (concat
    (describe-location location nodes)
    (describe-paths location edges)
    (describe-objects location objects object-locations)))

(defn walk [direction]
  (let [next (find-next-edge-at location direction edges)]
    (if (empty? next) '[you cannot go that way.]
                      (do (def location (first next))
                          (look)))))
