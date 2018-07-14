class CommonRequestPromise {
    constructor(){};

    get(url) {
        // Return a new promise.
        return new Promise(function(resolve, reject) {
            // Do the usual XHR stuff
            let req = new XMLHttpRequest();
            req.open('GET', url);

            req.onload = function() {
                // This is called even on 404 etc
                // so check the status
                if (req.status == 200) {
                    // Resolve the promise with the response text
                    resolve(req.response);
                }
                else {
                    // Otherwise reject with the status text
                    // which will hopefully be a meaningful error
                    reject(Error(req.statusText));
                }
            };

            // Handle network errors
            req.onerror = function() {
                reject(Error('Network Error'));
            };

            // Make the request
            req.send();
        });
    }

    post(url, params) {
        var data = JSON.stringify(params);
        // Return a new promise.
        return new Promise(function(resolve, reject) {
            // Do the usual XHR stuff
            let req = new XMLHttpRequest();
            req.open('POST', url, true)
            req.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

            req.onload = function() {
                if (req.status == 200) {
                    resolve(req.response);
                }
                else {
                    reject(Error(req.statusText));
                }
            };

            // Handle network errors
            req.onerror = function() {
                reject(Error('Network Error'));
            };

            // Make the request
            req.send(data);
        });
    }

    put(url, params) {
        var data = JSON.stringify(params);
        // Return a new promise.
        return new Promise(function(resolve, reject) {
            // Do the usual XHR stuff
            let req = new XMLHttpRequest();
            req.open('PUT', url, true)
            req.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

            req.onload = function() {
                if (req.status == 200) {
                    resolve(req.response);
                }
                else {
                    reject(Error(req.statusText));
                }
            };

            // Handle network errors
            req.onerror = function() {
                reject(Error('Network Error'));
            };

            // Make the request
            req.send(data);
        });
    }

    delete(url) {
        // Return a new promise.
        return new Promise(function(resolve, reject) {
            // Do the usual XHR stuff
            let req = new XMLHttpRequest();
            req.open('DELETE', url, true)
            req.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

            req.onload = function() {
                if (req.status == 200) {
                    resolve(req.response);
                }
                else {
                    reject(Error(req.statusText));
                }
            };

            // Handle network errors
            req.onerror = function() {
                reject(Error('Network Error'));
            };

            // Make the request
            req.send();
        });
    }
}