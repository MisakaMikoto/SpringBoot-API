class Signin {
    constructor() {}

    createAccount(params) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.post('/signin', params).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error("Failed!", error);
        });
    }
}