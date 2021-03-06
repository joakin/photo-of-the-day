# photo-of-the-day

[Live demo at
chimeces.com/photo-of-the-day](//chimeces.com/photo-of-the-day)

## Setup

Run `npm install` to get the npm deps installed.

To get an interactive development environment run:

    npm start     # On one terminal. Styles compilation.
    lein figwheel # On a different one, CLJS compilation and repl.

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    npm run build
    lein cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL.

## License

Copyright © 2015 Joaquin Oltra

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
