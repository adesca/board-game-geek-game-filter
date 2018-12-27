let currentGamesInView = {};

var updateGamesView = function (mechanicsArrayOrGameNameOrAll) {

    let url;
    if (typeof mechanicsArrayOrGameNameOrAll === 'string') {
        if (mechanicsArrayOrGameNameOrAll === 'ALL') {
            url = createFetchTopGamesUrl();
        } else {
            url = createGameSearchQueryUrl(mechanicsArrayOrGameNameOrAll);
        }

    } else {
        url = createMechanicsQueryUrl(mechanicsArrayOrGameNameOrAll);
    }

    fetchForJson(url.toString())
        .then(function (games) {
            clearExistingGamesFromView();

            games.forEach(function (game) {
                currentGamesInView[game.name] = game;
                createGameElement(game).then(newGameEl => {
                    addGameToView(newGameEl)
                })
            })
        })


}

addGameToView = (newGame) => {
    const containerDiv = document.getElementById('fetched-games-container');
    containerDiv.appendChild(newGame);
}

clearExistingGamesFromView = async function () {
    currentGamesInView = {};
    const containerDiv = document.getElementById('fetched-games-container');

    removeChildrenElements(containerDiv);
}

createGameElement = async function (game) {
    const newGameHtmlString = await interpolateRemoteTemplate('game-accordion-item.html', {game: game});

    return htmlToElement(newGameHtmlString);
};

createMechanicEls = function (mechanicsArr) {
    return "<li>" + mechanicsArr.map(mechanic => {
        return mechanic.value;
    }).join('</li><li>') + "</li>";
}

const selectNewMechanics = (gameName) => {
    const mechanicNames = currentGamesInView[gameName].mechanics.map((mechanic) => mechanic.value)
    updateMechanicsView(mechanicNames);
}

const getInfoForGameInView = (gameName) => {
    return currentGamesInView[gameName];
}