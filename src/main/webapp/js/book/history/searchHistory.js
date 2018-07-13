class SearchHistory {
    constructor(){};

    getList(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/history/' + memberId + '/id').then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    getListByQuery(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/history/' + memberId + '/query').then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    getListByDatetime(memberId) {
        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/history/' + memberId + '/datetime').then((response) => {
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
        return commonRequestPromise.delete('/v1/history/' + memberId + '?ids=' + ids).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }
}