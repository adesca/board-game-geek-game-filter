let currentMechanics = {};

fetch('/api/mechanics/all')
    .then(function (response) {
        if (response.ok) {
            return response.json()
        } else {
            console.error('there was an error ', response);
        }
    })
    .then(function (mechanics) {
        var containerDiv = document.getElementById('mechanics-container');

        mechanics.map(function (mechanic) {
            currentMechanics[mechanic.value] = mechanic;

            // IDs have to start with letters
            // https://stackoverflow.com/questions/20306204/using-queryselector-with-ids-that-are-numbers
            // https://stackoverflow.com/a/34777644
            const newInput = htmlToElement(`<input type="checkbox" id="m${mechanic.id}">`);
            const newLabel = htmlToElement(`<label for="m${mechanic.id}">${mechanic.value}</label>`);

            containerDiv.appendChild(newInput);
            containerDiv.appendChild(newLabel);
        });

        // containerDiv.appendChild()
    });

var queryGamesWithSelectedMechanics = function () {
    var allInputs = document.querySelectorAll('input');
    var selectedMechanics = [].filter.call(allInputs, function (input) {
        return input.checked;
    }).map(function (checkedInput) {
        // pull the mechanic text value from the label
        return document.querySelector(`label[for="${checkedInput.id}"]`)
            .textContent;
    });

    updateGamesView(selectedMechanics);

};

updateMechanicsView = function (mechanicsArr) {
    document.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
        checkbox.checked = false
    });

    mechanicsArr.forEach((mechanic) => {
        document.querySelector(`input#m${currentMechanics[mechanic].id}`).checked = true;
    })
};

