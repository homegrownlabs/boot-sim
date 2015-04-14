# boot-sim
[![Clojars Project](http://clojars.org/io.homegrown/boot-sim/latest-version.svg)](http://clojars.org/io.homegrown/boot-sim)

[Boot][1] tasks for interacting with a [Simulant][2] project.

Provides the following tasks:
* `bootstrap` - Bootstrap the backing Datomic database.
* `create-model` - Create a model in the database.
* `list-models` - List available models in the database.
* `create-test` - Create a test in the database.
* `list-tests` - List available tests in the database.
* `run-sim` - Run a sim based on a test.
* `list-sims` - List completed sims in the database.
* `validate-sim` - Validate a specific sim by id.
* `validate-latest` - Validate the latest sim for a given test.

## Usage

First, add `boot-sim` to your `build.boot` dependencies and `require` the
namespace:

```clj
(set-env! :dependencies '[[io.homegrown/boot-sim "X.Y.Z"]])
(require '[io.homegrown.boot-sim :refer :all])
```

Then, set any number of default task options to make your life easier:

```clj
(task-options!
  bootstrap       {:uri datomic-uri
                   :bootstrap-fn 'simulation.db/bootstrap!}
  create-model    {:uri datomic-uri
                   :create-fn 'simulation.model/create-model!
                   :type :model.type/sample}
  list-models     {:uri datomic-uri
                   :list-fn 'simulation.model/list-models}
  create-test     {:uri datomic-uri
                   :create-fn 'simulation.test/create-test!
                   :host-name "http://dockerhost:8080"
                   :duration 1
                   :concurrency 1}
  list-tests      {:uri datomic-uri
                   :list-fn 'simulation.test/list-tests}
  run-sim         {:uri datomic-uri
                   :run-fn 'simulation.sim/run-sim!
                   :processes 1
                   :speed 1}
  list-sims       {:uri datomic-uri
                   :list-fn 'simulation.sim/list-sims}
  validate-sim    {:uri datomic-uri
                   :validate-fn 'simulation.validations/validate}
  validate-latest {:uri datomic-uri
                   :validate-fn 'simulation.validations/validate
                   :lookup-fn 'simulation.sim/latest-sim})
```

## Examples

To run the full gamut from model creation, to sim validation, you would enter
the following commands:

```bash
$ boot bootstrap
$ boot create-model --model-name "Sample Model"
$ boot create-test --test-name "Sample Test" --model-name "Sample Model"
$ boot run-sim --test-name "Sample Test"
$ boot validate-latest --test-name "Sample Test"
```

## Extending exist tasks

You have two options for extending tasks if the need arises:

1. Copy the source of the task to your own `build.boot` and make the necessary
   adjustments. Add `:exclude [<task-name>]` to the `boot-sim` require.
2. Submit a Pull Request to this library.

If your changes are extremely project-specific or private, then replacing the
existing task is probably your best bet. If your changes are generally useful,
however, then we'd love to see them integrated into boot-sim.

## License

The MIT License (MIT)

Copyright (c) 2015 Homegrown Labs

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

[1]: https://github.com/tailrecursion/boot
[2]: https://github.com/Datomic/simulant
