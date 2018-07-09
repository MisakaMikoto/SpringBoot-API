class Login {
    constructor() {}

    triggerEnter() {
        $('#password').keydown((key) => {
            if (key.keyCode == 13) {
                $('#login').trigger('click');
            }
        });
    }

    findUser(id, password) {
        let params = {
            id: id,
            password: password
        };

        let commonPromise = new CommonPromise();
        return commonPromise.post('/account/login', params).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error("Failed!", error);
        });
    }
}