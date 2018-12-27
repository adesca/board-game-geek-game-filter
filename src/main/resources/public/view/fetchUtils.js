const createMechanicsQueryUrl = (mechanicsArray) => {
    var url;
    var searchParams = new URLSearchParams();
    url = new URL('/api/games', location.origin);
    // /games/search/findGamesWithMechanics

    mechanicsArray.forEach(function (mechanic) {
        searchParams.append("mechanics", mechanic)
    });

    url.search = searchParams.toString();

    return url;
};

const createFetchTopGamesUrl = () => {
    return new URL('/api/games/top', location.origin);
}

const createGameSearchQueryUrl = (gameName) => {
    const searchParams = new URLSearchParams();
    // searchParams.append('projection', 'fullGameProjection');
    searchParams.append('name', gameName);
    const url = new URL('/games/search/findGamesByName', location.origin)

    url.search = searchParams.toString();
    return url;
}

const fetchForJson = (path) => {
    return fetch(path).then(function (response) {
        if (response.ok) {
            return response.json();
        } else {
            console.log('there was an error ', response);
            throw new Error("Non 200 status code for fetch " + response.status);
        }
    })
        .then(responseJson => {
            if (responseJson._embedded) {
                if (responseJson._embedded.games) {
                    return Promise.resolve(responseJson._embedded.games)
                } else {
                    return Promise.resolve(responseJson._embedded)
                }
            } else {
                return Promise.resolve(responseJson);
            }
        })
};
