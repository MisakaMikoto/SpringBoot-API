class Login {
    constructor() {}

    findUser(id, password) {
        let params = {
            id: id,
            password: password
        };

        let commonPromise = new CommonPromise();
        return commonPromise.post('/login', params).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error("Failed!", error);
        });
    }
}