class Login {
    constructor() {}

    findUser(id, password) {
        let params = {
            id: id,
            password: password
        };

        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.post('/v1/account/login', params).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error("Failed!", error);
        });
    }

    validLogin() {
        if($('#id').val().trim().length == "") {
            alert("아이디를 입력해 주세요.");
            return false;

        } else if($('#password' ).val() == "") {
            alert("암호를 입력해 주세요.");
            return false;

        } else {
            return true;
        }
    }
}