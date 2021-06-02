var check = false;
let input_file = document.getElementById('inputFile');
let img_type1 = ["jpg","png","gif","bmp","tiff"];
let img_type2 = ["image/jpeg","image/png","image/gif","image/bmp","image/tiff"];
let n_download = 5;
let type_load = 0;
let image;

function select_image(target) {
    check = false;
    if (target.files &&
        target.files.length > 0 &&
        target.files[0] &&
        target.files[0].size > 0 &&
        input_file.value !== '') {
        image = target.files[0];
        for (let i = 0; i < n_download; i++) {
            if (image.type == img_type2[i]) {
                view_image(image);
                type_load = i;
                check = true;
                document.getElementsByName('radio3')[i].checked = true;
                return;
            }
        }
        document.getElementById('file1').alt = "Упс. Что-то пошло не так. Проверь тип файла";
        document.getElementById('file1').src = "";
        check = false;
    }
}

    function view_image(image){
        let image2 = new Image();
        let reader = new FileReader();
        reader.onload = function () {
            document.getElementById('file1').src = reader.result;
        };
        reader.readAsDataURL(image);
        check = true;
    }

    async function new_lot() {
        let forma = new FormData();
        let name = document.getElementById("name").value;
        let type = document.getElementById("type").selectedIndex;
        let description = document.getElementById("description").value;
        let price = document.getElementById("price").value;
        let increase = document.getElementById("increase").value;
        let startTime = document.getElementById("startTime").value;
        let duration = document.getElementById("duration").value;

        if(name != "") {
            if (type != "") {
                if (description != "") {
                    if (price != "") {
                        if (increase != "") {
                            if (startTime) {
                                if (duration != "") {
                                    forma.set("load_image", "false");
                                    if(check) {
                                        forma.append('image', image);
                                        forma.set("load_image", "true");
                                    }
                                    forma.set('name', name);
                                    forma.set("type", type);
                                    forma.set("description", description);
                                    forma.set("price", price);
                                    forma.set("increase", increase);
                                    forma.set("startTime", startTime);
                                    forma.set("duration", duration);
                                    forma.set("login", sessionStorage.getItem('login'));
                                    forma.set("hash", sessionStorage.getItem('hash'));
                                    let response = await fetch('http://localhost:8080/ubay_war/seller',{
                                        method: 'POST', body: forma, enctype: 'multipart/form-data'
                                    });
                                    if (response.ok) {
                                        document.getElementById('lot_in').textContent = "Лот успешно создан";
                                        }
                                } else
                                    document.getElementById('lot_in').textContent = "Поле продолжительность пустое";
                            } else
                                document.getElementById('lot_in').textContent = "Поле начало аукциона пустое";
                        } else
                            document.getElementById('lot_in').textContent = "Поле прирост ставки пустое";
                    } else
                        document.getElementById('lot_in').textContent = "Поле стартовая цена пустое";
                } else
                    document.getElementById('lot_in').textContent = "Поле описание пустое";
            } else
                document.getElementById('lot_in').textContent = "Поле тип техники пустое";
        } else
            document.getElementById('lot_in').textContent = "Поле название пустое";
        }

async function winner() {
    let forma = new FormData();
    let login = sessionStorage.getItem('login');
    let hash = sessionStorage.getItem('hash');
    let lot = document.getElementById("lot").value;

    if(lot != "") {
        let send_json = {
            login: login,
            hash: hash,
            lot: lot,
        };
        let jsonString = JSON.stringify(send_json);
        let response = await fetch('http://localhost:8080/ubay_war/winner', {
            method: 'POST',
            headers: {"Content-Type": "application/json;charset=utf-8"},//
            body: JSON.stringify(send_json)
        });
        if (response.ok) {
            let res = await response.json();
            if (res.winnerCreated == "true") {
                document.getElementById('it').textContent = res.winnerName;
                document.getElementById('win').textContent = "Победитель найден";
            } else
                document.getElementById('win').textContent = res.trouble;
        } else
            document.getElementById('win').textContent = "Что то пошло не так";
    } else
        document.getElementById('win').textContent = "Поле номер лота пустое";
}
