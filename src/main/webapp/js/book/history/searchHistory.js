class SearchHistory {
    constructor(){};

    getList(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/histories?memberId=' + memberId).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    getListByQuery(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/histories/query?memberId=' + memberId).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    getListByDatetime(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/histories/datetime?memberId=' + memberId).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    deleteList(memberId) {
        let ids = "";
        $('input[type=checkbox]:checked').each( (index, element) => {
            if(index == 0) {
                ids += $(element).attr('id');
            } else {
                ids += "," + $(element).attr('id');
            }
        });

        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.delete('/histories?memberId=' + memberId + '&ids=' + ids).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }
}