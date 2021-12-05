<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Phòng chờ</title>
<link rel="stylesheet" href="css/EntryGame.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
</head>

<body>
    <header>
        Chào mừng, ID room của bạn là:
        <!--code-->
    </header>
    <section>
        <div class="entry-button">
            <div class="entry-button-left">
                <input type="button" class="btn-general btn-big" value="Thoát">
            </div>
            <div class="entry-button-right">
                <button type="button" id="btnLock" class="btn-general btn-small"><i class="fas fa-lock"></i></button>
                <input type="button" class="btn-general btn-big" value="Bắt đầu">
            </div>
        </div>

        <div class="entry-players">
            <!--Thêm code hiển thị các người chơi-->
            <div class="player-name">
                <b>Player nè!</b>
            </div>
            <!--<div class="player-name">
                    <b>Player 2!</b>
                </div>
                <div class="player-name">
                    <b>Player 3!</b>
                </div>-->
        </div>
    </section>
</body>
</html>