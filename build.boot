(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure      "1.6.0"      :scope "provided"]
                  [boot/core                "2.0.0-rc12" :scope "provided"]
                  [com.datomic/datomic-free "0.9.5130"   :scope "provided"
                                                         :exclusions [joda-time
                                                                      org.slf4j/slf4j-nop
                                                                      org.slf4j/slf4j-log4j12]]
                  [adzerk/bootlaces          "0.1.11"    :scope "test"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.2.2")

(bootlaces! +version+)

(task-options!
  pom {:project     'io.homegrown/boot-sim
       :version     +version+
       :description "Boot tasks for running simulation tests."
       :url         "https://github.com/homegrownlabs/boot-sim"
       :scm         {:url "https://github.com/homegrownlabs/boot-sim"}
       :license     {"The MIT License"
                     "http://opensource.org/licenses/MIT"}})
