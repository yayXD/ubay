async function loadLot() {
    var parameters = location.search.substring(1).split("&")
    var temp = parameters[0].split("=");
    let send_json = {
        lotID: temp[1],
    };

    let response = await fetch('http://localhost:8080/ubay_war/loadlot', {
        method: 'POST',
        headers: {"Content-Type": "application/json;charset=utf-8"},//
        body: JSON.stringify(send_json)
    });
    if(response.ok) {
        let res = await response.json();
        let image = document.getElementById('img1');
        image.src = "/ubay_war/loadimage?imageName=" + res.imageName;
        document.getElementById('price').textContent = "Цена покупки:  " + res.lotPrice + "  грн";
        document.getElementById('lotId').textContent = "Артикул:  " + res.lotId ;
        document.getElementById('lotStatus').textContent = "Статус лота:  " + res.lotStatus ;
        document.getElementById('lotType').textContent = "Категория:  " + res.lotType ;
        document.getElementById('lotDescription').textContent = "Описание:  " + res.lotDescription
        document.getElementById('lotWinner').textContent = "Победитель:  " + res.lotWinner ;
        document.getElementById('lotFeedback').textContent = "Отзыв:  " + res.lotFeedback ;
    }
}
