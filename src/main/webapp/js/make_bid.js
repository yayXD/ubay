async function make_bid() {
    let forma = new FormData();
    let itemNumber = document.getElementById('itemNumber1').value;
    let bid = document.getElementById("bid").value;
    let loginBuyer =  sessionStorage.getItem('login');
    let hash = sessionStorage.getItem('hash');

    if (itemNumber != "") {
        if (bid != "") {
            let send_json = {
                itemNumber: itemNumber,
                bid: bid,
                loginBuyer: loginBuyer,
                hash: hash,
            };
            let jsonString = JSON.stringify(send_json);
            let response = await fetch('http://localhost:8080/ubay_war/bid', {
                method: 'POST',
                headers: {"Content-Type": "application/json;charset=utf-8"},//
                body: JSON.stringify(send_json)
            });
            if (response.ok) {
                let res = await response.json();
                if (res.bidCreated == "true")
                            document.getElementById('check').textContent = "Cтавка сделана";
                        else
                            document.getElementById('check').textContent = res.trouble;
                    }
        } else
            document.getElementById('check').textContent = "Поле сумма ставки пустое";
    } else
        document.getElementById('check').textContent = "Поле код лота пустое";
}

async function change(role) {
    let forma = new FormData();
    let login = sessionStorage.getItem('login');
    let password_old = document.getElementById('password').value;
    let password_new = document.getElementById('password2').value;
    let passwod = document.getElementById('password3').value;
    let hash = sessionStorage.getItem('hash');

    if(password_old != "") {
        if(password_new != "") {
            if(passwod != "" && passwod === password_new) {
                let send_json = {
                    login: login,
                    password_new: password_new,
                    password_old: password_old,
                    hash: hash,
                    role: role,
                };
                let jsonString =JSON.stringify(send_json);
                let response = await fetch('http://localhost:8080/ubay_war/change', {
                    method: 'POST',
                    headers: {"Content-Type": "application/json;charset=utf-8"},//
                    body: JSON.stringify(send_json)
                });
                if (response.ok) {
                    let res = await response.json();
                    if (res.changeCreated == "true")
                        document.getElementById('password_change').textContent = "Пароль изменен";
                    else
                         document.getElementById('password_change').textContent = res.trouble;
                }
            } else
                document.getElementById('password_change').textContent = "Поле подтверждение нового пароля пустое";
        } else
            document.getElementById('password_change').textContent = "Поле новый пароль пустое";
    } else
        document.getElementById('password_change').textContent = "Поле старый пароль пустое";
}

 async function feedback() {
     let forma = new FormData();
     let login = sessionStorage.getItem('login');
     let lotItem = document.getElementById('itemNumber2').value;
     let mark = document.getElementById('mark').value;
     let feedback = document.getElementById('feedback').value;
     let hash = sessionStorage.getItem('hash');

     if(lotItem != "") {
         if(mark != "1") {
             if(feedback != "") {
                 let send_json = {
                     login: login,
                     lotItem: lotItem,
                     mark: mark,
                     feedback: feedback,
                     hash: hash,
                     user: "1",
                 };
                 let jsonString =JSON.stringify(send_json);
                 let response = await fetch('http://localhost:8080/ubay_war/feedback', {
                     method: 'POST',
                     headers: {"Content-Type": "application/json;charset=utf-8"},//
                     body: JSON.stringify(send_json)
                 });
                 if (response.ok) {
                     let res = await response.json();
                     if (res.feedbackCreated == "true")
                         document.getElementById('in').textContent = "фидбек оставлен";
                     else
                         document.getElementById('in').textContent = res.trouble;
                 }
             } else
                 document.getElementById("in").textContent = "Поле отзыв пустое";
         } else
             document.getElementById("in").textContent = "Вы не выполнили оценку";
     } else
         document.getElementById("in").textContent = "Поле код лота пустое";
 }
