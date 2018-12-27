var updateGamesView = function (mechanicsArray) {
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

            console.log('val ', value._embedded.games);
            // debugger;
            while (containerDiv.firstChild) {
                containerDiv.removeChild(containerDiv.firstChild);
            }

            value._embedded.games.forEach(function (game) {
                console.log('here', game);
                createGameElement(game).then(newGameEl => {
                    containerDiv.appendChild(newGameEl);
                })
            })
        })
}

createGameElement = async function (game) {
    const newGameHtmlString = await interpolateRemoteTemplate('game-accordion-item.html', {game: game});

    return htmlToElement(newGameHtmlString);
};

createMechanicEls = function (mechanicsArr) {
    return mechanicsArr.map(mechanic => {
        return mechanic.value;
    }).join(' ');
}