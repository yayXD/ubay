 async function registr() {
     let forma = new FormData();
     let login = document.getElementById("login").value;
     let password = document.getElementById("password").value;
     let password2 = document.getElementById("password2").value;
     let role = document.getElementById("role").selectedIndex;
     let email = document.getElementById("email").value;

     if (login != "") {
         if (password != "") {
             if(email != "") {
                 if (password === password2) {
                     let send_json = {
                         login: login,
                         password: password,
                         role: role,
                         email: email
                     };
                     let jsonString = JSON.stringify(send_json);
                     let response = await fetch('http://localhost:8080/ubay_war/registration', {
                         method: 'POST',
                         headers: {"Content-Type": "application/json;charset=utf-8"},//
                         body: JSON.stringify(send_json)
                     });
                     if (response.ok) {
                         let res = await response.json();
                         if (res.userCreated == "true")
                             document.getElementById('label_registr').textContent = "Регистрация прошла успешно. Выполните вход";
                         else
                             document.getElementById('label_registr').textContent = res.userCreated;
                     }
                 } else
                     document.getElementById('label_registr').textContent = "Не правильное подтверждение пароля";
             }else
                 document.getElementById('label_registr').textContent = "Поле емейл пустое";
             } else
                 document.getElementById('label_registr').textContent = "Поле пароля пустое";
         } else
             document.getElementById('label_registr').textContent = "Поле логина пустое";
     }

 async function sign_in() {
     let forma = new FormData();
     sessionStorage.setItem('login', "");
     sessionStorage.setItem('hash', "");

     let login = document.getElementById("login2").value;
     let password = document.getElementById("password2").value;
     if(login != "") {
         if(password != "") {
             let send_json = {
                 login: login,
                 password: password,
             };
             let jsonString = JSON.stringify(send_json);
             let response = await fetch('http://localhost:8080/ubay_war/sign_in', {
                 method: 'POST',
                 headers: {"Content-Type": "application/json;charset=utf-8"},//
                 body: JSON.stringify(send_json)
             });
            if(response.ok){
                let res = await response.json();
                if(res.loginExist == "true"){
                    if(res.checkPass == "true"){
                        document.getElementById("label_log_in").textContent = "Вход успешно выполнен"
                        sessionStorage.setItem('login', login);
                        sessionStorage.setItem('hash', res.hash);
                        if(res.role == "0") {
                            document.location.href = "/ubay_war/jsp/seller.jsp";
                        }else{
                            document.location.href = "/ubay_war/jsp/customer.jsp"
                        }
                    }
                    else {
                        document.getElementById("label_log_in").textContent = res.checkPass;
                    }
                }
                else
                    document.getElementById("label_log_in").textContent = res.loginExist;
            }
         } else {
             document.getElementById("label_log_in").textContent = "Введен неверный пароль";
         };

     } else {
         document.getElementById("label_log_in").textContent = "Введите логин";
     };
 }

 async function forgot(){
     let forma = new FormData();
     let login = document.getElementById("login2").value;
     if(login != "") {
         let send_json = {
             login: login,
         };
         let jsonString = JSON.stringify(send_json);
         let response = await fetch('http://localhost:8080/ubay_war/forgot', {
             method: 'POST',
             headers: {"Content-Type": "application/json;charset=utf-8"},//
             body: JSON.stringify(send_json)
         });
         if (response.ok) {
             let res = await response.json();
             if (res.loginExist =="true") {
                 document.getElementById("forgot").textContent = "Пароль был отправлен на почту"
         } else
             document.getElementById("forgot").textContent = res.loginExist;
        }
     } else {
         document.getElementById("forgot").textContent = "Введите логин";
     };
 }

 async function funonload(type,text) {
     const main = document.querySelector('main');
     let send_json = {
         type: type,
         text: text,
         N: 10,
     };

     let response = await fetch('http://localhost:8080/ubay_war/infoload', {
         method: 'POST',
         headers: {"Content-Type": "application/json;charset=utf-8"},//
         body: JSON.stringify(send_json)
     });
     if (response.ok) {
         let res = await response.json();
         let size = parseInt(res.size, 10);
         for (let a = 0; a < size; a++) {
             const section = document.createElement('section');
             const heading = document.createElement('h2');
             const para = document.createElement('p');
             section.setAttribute('class', "lots");
             section.setAttribute('name', "loadedLot");
             section.setAttribute('id', "loadedLot"+ a);
             let image = new Image(100);
             image.src = "/ubay_war/loadimage?imageName=" + res.imageName[0][a];
             const href = document.createElement('a');
             href.title = res.lotName[0][a];
             href.href = "/ubay_war/jsp/bigLot.jsp?lotId=" + res.lotId[0][a];
             image.alt = "error load image";
             href.appendChild(document.createTextNode("    " + res.lotName[0][a]));
             main.appendChild(section);
             section.appendChild(heading);
             section.appendChild(para);
             section.appendChild(image);
             section.appendChild(href);
         }
     }
 }

 function printLogin() {
     document.getElementById('logIn').textContent = "Логин:  " + sessionStorage.getItem('login');
}

async function exitLog() {
    let login =  sessionStorage.getItem('login');
    sessionStorage.setItem('login', "");
    sessionStorage.setItem('hash', "");
    let forma = new FormData();
    let send_json = {
        login: login,
    };
    let jsonString = JSON.stringify(send_json);
    let response = await fetch('http://localhost:8080/ubay_war/exit', {
        method: 'POST',
        headers: {"Content-Type": "application/json;charset=utf-8"},//
        body: JSON.stringify(send_json)
    });
    if (response.ok) {
        document.location.href = "/ubay_war/jsp/index.jsp";
    }
}

function findUbay() {
    let ind = 0;
    let lots=document.getElementsByName('loadedLot');
        if(lots != null) {
            for (let i = 0; lots.length != 0; i++) {
                lots[0].remove();
            }
    }

    let rad=document.getElementsByName('radio3');
    for (let i=0;i<rad.length; i++) {
        if (rad[i].checked) {
            ind = i;
        }
    }
    let text = document.getElementById('search').value;
    funonload(ind,text);

}