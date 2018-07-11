class Login {
    constructor() {}

    findUser(id, password) {
        let params = {
            id: id,
            password: password
        };

        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.post('/login', params).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error("Failed!", error);
        });
    }
}