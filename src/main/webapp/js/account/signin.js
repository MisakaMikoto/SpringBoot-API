class Signin {
    constructor() {}

    createAccount(params) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.post('/v1/account/signin', params).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error("Failed!", error);
        });
    }

    validSignin() {
        if($('#id').val().trim().length == "") {
            alert("아이디를 입력해 주세요.");
            return false;

        } else if($('#name' ).val() == "") {
            alert("이름을 입력해 주세요.");
            return false;

        } else if($('#password' ).val() == "") {
            alert("암호를 입력해 주세요.");
            return false;

        } else {
            return true;
        }
    }
}