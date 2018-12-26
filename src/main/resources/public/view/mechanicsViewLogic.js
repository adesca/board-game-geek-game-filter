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
                var newInput = document.createElement('input');
                newInput.setAttribute('type', 'checkbox');
                newInput.id = mechanic.id;

                var newLabel = document.createElement('label');
                newLabel.setAttribute('for', mechanic.id);
                newLabel.textContent = mechanic.value;

                containerDiv.appendChild(newInput);
                containerDiv.appendChild(newLabel);
            });

            // containerDiv.appendChild()
        })

var updateGameView = function () {
    var allInputs = document.querySelectorAll('input');
    var selectedMechanics = [].filter.call(allInputs, function (input) {
        return input.checked;
    }).map(function (checkedInput) {
        return document.querySelector(`label[for="${checkedInput.id}"]`)
            .textContent;
    })

    updateGames(selectedMechanics);

};