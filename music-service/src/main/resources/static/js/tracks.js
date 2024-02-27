$.fakeLoader({
    bgColor: "#34495e",
    spinner: "spinner3"
});

const trackReleaseDatapicker = $('#track-release-dapapicker');
trackReleaseDatapicker.datepicker({
    format: 'yyyy-mm-dd'
});

const albumReleaseDatapicker = $('#album-release-dapapicker');
albumReleaseDatapicker.datepicker({
    format: 'yyyy-mm-dd'
});

const editTrackModal = $('#editTrackModal');

const editAlbumModal = $('#editAlbumModal');

const selectAlbums = createAlbumsSelect('#edit-album-select');

const selectGenres = createGenreSelect('#edit-genres-select');

function createAlbumsSelect(selector) {
    return new TomSelect(selector, {
        preload: true,
        load: function (query, callback) {
            fetch("/api/albums")
                .then((response) => response.json())
                .then((data) => {
                    if (!data.statusCode) {
                        let json = data.map(function (album) {
                            return {
                                value: album.id,
                                text: album.title + " (" + album.artist.name + ")"
                            };
                        });
                        callback(json);
                    } else {
                        callback();
                    }
                });
        },
        create: function (input) {
            let albumTitle = input.trim();
            $('#edit-album-title').val(albumTitle);
            editAlbumModal.modal('show');
        },
        // Перевод надписей
        render: {
            option_create: function (data, escape) {
                return '<div class="create">Create Album: <strong>' + escape(data.input) + '</strong>…</div>';
            },
            no_results: function (data, escape) {
                return '<div class="no-results">Album not found</div>';
            },
        }
    });
}


function createArtistSelect(selector) {
    return new TomSelect(selector, {
        preload: true,
        // Загрузка через API
        load: function (query, callback) {
            fetch("/api/artists")
                .then((response) => response.json())
                .then((data) => {
                    if (!data.statusCode) {
                        let json = data.map(function (album) {
                            return {
                                value: album.id,
                                text: album.name
                            };
                        });
                        callback(json);
                    } else {
                        callback();
                    }
                });
        },
        create: function (input) {
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '/api/artists',
                data: JSON.stringify({"name": input}),
                success: () => {
                    reloadSelectOptions(this);
                },
                error: function (xhr, status, error) {
                    showErrorAlert(`Error during create Artist: ${xhr.responseJSON.status} ${xhr.responseJSON.error}`);
                }
            });
        },
        createFilter: function (input) {
            input = input.toLowerCase();
            return (!(input in this.options)) && input.length >= 2 && input.length <= 50;
        },
        render: {
            option_create: function (data, escape) {
                return '<div class="create">Create Artist: <strong>' + escape(data.input) + '</strong>…</div>';
            },
            no_results: function (data, escape) {
                return '<div class="no-results">Artist not found</div>';
            },
        }
    });
}

function createGenreSelect(selector) {
    return new TomSelect(selector, {
        preload: true,
        load: function (query, callback) {
            fetch("/api/genres")
                .then((response) => response.json())
                .then((data) => {
                    if (!data.statusCode) {
                        let json = data.map(function (genre) {
                            return {
                                value: genre.id,
                                text: genre.name
                            };
                        });
                        callback(json);
                    } else {
                        callback();
                    }
                });
        },
        create: function (input) {

            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '/api/genres',
                data: JSON.stringify({"name": input}),
                success: () => {
                    reloadSelectOptions(this);
                },
                error: function (xhr, status, error) {
                    showErrorAlert(`Error during create Genre: ${xhr.responseJSON.status} ${xhr.responseJSON.error}`);
                }
            });
        },
        createFilter: function (input) {
            input = input.toLowerCase();
            return (!(input in this.options)) && input.length >= 2 && input.length <= 50;
        },
        render: {
            option_create: function (data, escape) {
                return '<div class="create">Create Genre: <strong>' + escape(data.input) + '</strong>…</div>';
            },
            no_results: function (data, escape) {
                return '<div class="no-results">Genre not found</div>';
            },
        }
    });
}

function reloadSelectOptionsAndSet(select, optionValueId) {
    select.settings.load.call(select, '', function () {
        select.loadCallback.apply(select, arguments);
        select.setValue(optionValueId);
    });
}

function reloadSelectOptions(select) {
    select.settings.load.call(select, '', function () {
        select.loadCallback.apply(select, arguments);
    });
}

const editTrackForm = $('#editTrackForm');

const inputTrackId = $('#edit-track-id');

const inputTrackTitle = $('#edit-track-title');
const errorTrackTitle = $('#trackTitleErrorLabel');

const inputTrackMinutes = $('#edit-length-minutes');
const inputTrackSeconds = $('#edit-length-seconds');
const errorTrackLength = $('#lengthErrorLabel');

const inputTrackRelease = $('#track-edit-release');
const errorTrackRelease = $('#trackReleaseErrorLabel');

const inputTrackAlbum = $('#edit-album-select');
const errorTrackAlbum = $('#albumErrorLabel');

const inputTrackText = $('#edit-text');
const errorTrackText = $('#textErrorLabel');

const inputTrackGenre = $('#edit-genres-select');
const errorTrackGenre = $('#genresErrorLabel');

const trackKeyInputErrorLabelMap = new Map()
    .set("id", [[inputTrackId], [() => inputTrackId.val()], [null, null], null])
    .set("title", [[inputTrackTitle], [() => inputTrackTitle.val()], [null, null], errorTrackTitle])
    .set("length", [[inputTrackMinutes, inputTrackSeconds], [() => inputTrackMinutes.val(), () => inputTrackSeconds.val()],
        [calcLengthToMinutesAndSeconds, calcMinutesAndSecondsToLength], errorTrackLength])
    .set("release", [[inputTrackRelease], [() => inputTrackRelease.val()], [trackReleaseInput, null], errorTrackRelease])
    .set("album", [[selectAlbums], [() => selectAlbums.getValue()], [null, wrapToJsonWithId], errorTrackAlbum])
    .set("text", [[inputTrackText], [() => inputTrackText.val()], [null, null], errorTrackText])
    .set("genres", [[selectGenres], [() => selectGenres.getValue()], [null, wrapToJsonWithId], errorTrackGenre]);

function calcLengthToMinutesAndSeconds(minSecInputs, length) {
    const seconds = length % 60;
    const minutes = (length - seconds) / 60;
    minSecInputs[0].val(minutes);
    minSecInputs[1].val(seconds);
}

function trackReleaseInput(inputs, date) {
    inputs[0].val(date);
    trackReleaseDatapicker.datepicker("update", date);
}

function calcMinutesAndSecondsToLength(valuesFunc) {
    return (parseInt(valuesFunc[0]()) * 60) + parseInt(valuesFunc[1]());
}

editTrackModal.on('show.bs.modal', function (event) {

    if (event.target.id === editTrackModal.attr('id')) {

        resetFormData(trackKeyInputErrorLabelMap);

        const trackId = $(event.relatedTarget).data('track-id');

        const isAddForm = !(trackId);

        if (isAddForm) {
            trackReleaseDatapicker.datepicker("update", '');
        } else {
            setFormDataFromResponse('/api/tracks/' + trackId, trackKeyInputErrorLabelMap);
        }

        editTrackForm.off('submit').on('submit', function (event) {

            event.preventDefault();

            $.ajax({
                type: isAddForm ? "POST" : "PUT",
                url: isAddForm ? "/api/tracks" : "/api/tracks/" + trackId,
                data: JSON.stringify(serializeFormData(trackKeyInputErrorLabelMap, isAddForm)),
                contentType: 'application/json',
                success: function (response) {
                    editTrackModal.modal('hide');
                    showSuccessAlert(isAddForm ? "Track created!" : "Track edited!");
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    if (xhr.responseJSON && xhr.responseJSON.code === "VALIDATION_FAILED") {
                        showErrorAlert("Validation error!");
                        setValidationErrors(trackKeyInputErrorLabelMap, xhr.responseJSON.fields);
                    } else {
                        showErrorAlert('Error: ' + error);
                    }
                }
            });

        });

    }

});

const editAlbumForm = $('#editAlbumForm')

const inputAlbumTitle = $('#edit-album-title');
const errorAlbumTitle = $('#titleErrorLabel');

const inputAlbumRelease = $('#album-edit-release');
const errorAlbumRelease = $('#albumReleaseErrorLabel');

const selectArtists = createArtistSelect('#edit-artist-select');
const errorArtists = $('#artistErrorLabel');

const albumKeyInputErrorLabelMap = new Map()
    .set("title", [[inputAlbumTitle], [() => inputAlbumTitle.val()], [null, null], errorAlbumTitle])
    .set("release", [[inputAlbumRelease], [() => inputAlbumRelease.val()], [null, null], errorAlbumRelease])
    .set("artist", [[selectArtists], [() => selectArtists.getValue()], [null, wrapToJsonWithId], errorArtists]);

editAlbumModal.on('show.bs.modal', function (event) {

    editAlbumForm.off('submit').on('submit', function (event) {

        event.preventDefault();

        $.ajax({
            type: "POST",
            url: "/api/albums",
            data: JSON.stringify(serializeFormData(albumKeyInputErrorLabelMap)),
            contentType: 'application/json',
            success: function (response) {
                editAlbumModal.modal('hide');
                reloadSelectOptionsAndSet(selectAlbums, response.id);
                showSuccessAlert("Album created. Please add tracks to it.")
                resetFormData(albumKeyInputErrorLabelMap);
            },
            error: function (xhr, status, error) {
                if (xhr.responseJSON && xhr.responseJSON.code === "VALIDATION_FAILED") {
                    showErrorAlert("Validation error!");
                    setValidationErrors(albumKeyInputErrorLabelMap, xhr.responseJSON.fields);
                } else {
                    showErrorAlert('Произошла ошибка: ' + error);
                }
            }
        });

    });

});

function setFormDataFromResponse(url, formMap) {

    $.get(url, function (response) {

        for (let [key, value] of formMap) {
            const inputs = value[0];
            const inputDataWrapper = value[2][0];
            const responseData = response[key];

            if (inputDataWrapper) {
                inputDataWrapper(inputs, responseData);
            } else {
                if (inputs[0] instanceof TomSelect) {
                    if (Array.isArray(responseData)) {
                        inputs[0].setValue(responseData.map(obj => obj.id));
                    } else {
                        inputs[0].setValue(responseData.id);
                    }
                } else {
                    inputs[0].val(responseData);
                }
            }
        }
    });
}

function iterateJsonByFields(obj) {
    for (let key in obj) {
        if (typeof obj[key] === "object" && obj[key] !== null) {
            console.log(key + ":");
            iterateJsonByFields(obj[key]); // Рекурсивный вызов для перебора вложенных объектов
        } else {
            console.log(key + ": " + obj[key]);
        }
    }
}

function setFormData(formMap) {
    for (let [key, value] of formMap) {
        const inputs = value[0];
        inputs.forEach(input => {
            if (input instanceof TomSelect) {
                input.clear();
            } else {
                input.val('');
            }
        });
    }
}


function resetFormData(formMap) {
    for (let [key, value] of formMap) {
        const inputs = value[0];
        inputs.forEach(input => {
            if (input instanceof TomSelect) {
                input.clear();
            } else {
                input.val('');
            }
        });
    }
}

function resetValidationErrors(formMap) {
    for (let [key, value] of formMap) {
        const inputs = value[0];
        const errorLabel = value[2];

        inputs.forEach(input => {
            if (input instanceof TomSelect) {
                $(input.input).next().removeClass("is-invalid");
            } else {
                input.removeClass("is-invalid");
            }
        });

        errorLabel.text('');
    }

}


function setValidationErrors(formMap, fieldErrors) {
    fieldErrors.forEach((fieldError) => {
        const errorField = formMap.get(fieldError.name);
        if (errorField) {
            const inputFields = errorField[0];
            const errorLabel = errorField[3];

            inputFields.forEach(inputField => {
                if (inputField instanceof TomSelect) {
                    $(inputField.input).next().addClass("is-invalid");
                } else {
                    inputField.addClass("is-invalid");
                }
            });
            errorLabel.text(fieldError.message);
        }
    });
}

function serializeFormData(formMap, isAddForm) {
    const formData = {};

    for (let [key, value] of formMap) {
        const errorLabel = value[3];
        if ((isAddForm && errorLabel) || (!isAddForm)) {
            const inputDataFuncArr = value[1];
            const wrapperFunction = value[2][1]; // 2я ([1]) по счету функция - для отправки на бэк
            if (wrapperFunction) {
                formData[key] = wrapperFunction(inputDataFuncArr);
            } else {
                formData[key] = inputDataFuncArr[0]();
            }
        }
    }

    return formData;
}


function wrapToJsonWithId(inputDataFuncArr) {
    const value = inputDataFuncArr[0]();

    console.log(value);
    if (isEmpty(value)) return null;

    if (Array.isArray(value)) {
        return value.map(item => ({id: parseInt(item)}));
    } else {
        return {id: parseInt(value)};
    }

}

function isEmpty(value) {
    return (value == null || (typeof value === "string" && value.trim().length === 0));
}

const deleteObjectModal = $('#deleteObjectModal');
const deleteObjectButton = $('#deleteObjectButton');

deleteObjectModal.on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    let url = button.data('url');
    let name = button.data('name')
    $('#deleteObjectName').text(name);

    deleteObjectButton.on('click', function () {
        deleteObjectRequest(url);
    });

});

function deleteObjectRequest(url) {
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function () {
            showSuccessAlert('Resource deleted successfully')
            window.location.reload();
        },
        error: function (error) {
            showErrorAlert('Error:' + error)
        }
    });
}

function showErrorAlert(message) {
    const errorAlert = $('#errorAlert');
    errorAlert.find('.toast-body').text(message);
    const toast = new bootstrap.Toast(errorAlert);
    toast.show();
}

function showSuccessAlert(message) {
    const errorAlert = $('#successAlert');
    errorAlert.find('.toast-body').text(message);
    const toast = new bootstrap.Toast(errorAlert);
    toast.show();
}