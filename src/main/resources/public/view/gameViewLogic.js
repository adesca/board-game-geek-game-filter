var updateGames = function (mechanicsArray) {
    console.log('bleh');

    var url;
    var searchParams = new URLSearchParams();
    if (mechanicsArray.length > 1) {
        url = new URL('/games/search/findGamesWithMechanics', location.origin);
        // /games/search/findGamesWithMechanics

        mechanicsArray.forEach(function (mechanic) {
            searchParams.append("mechanics", mechanic)
        });
    } else {
        url = new URL('/games', location.origin);
        searchParams.append('mechanic.value', mechanicsArray[0])
    }

    url.search = searchParams.toString();

    fetch(url.toString()).then(function (response) {
        if (response.ok) {
            return response.json();
        } else {
            console.log('there was an error');
        }
    })
        .then(function (value) {
            var containerDiv = document.getElementById('fetched-games-container');

            while(containerDiv.firstChild) {
                containerDiv.removeChild(containerDiv.firstChild);
            }

            value._embedded.games.forEach(function (game) {
                var newGame = document.createElement('div');
                var textNode = document.createTextNode(game.name);
                newGame.appendChild(textNode);

                console.log('here');
                containerDiv.appendChild(newGame);
            })
        })
}

function getData(url, data) {

}