<%-- 
    Document   : signup
    Created on : 24/09/2024, 22:42:54
    Author     : jorge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }
        .container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            padding: 40px;
            width: 100%;
            max-width: 600px;
        }
        h1 {
            color: #003DA5;
        }
        .btn-primary {
            background-color: #003DA5;
            border: none;
        }
        .btn-primary:hover {
            background-color: #002A80;
        }
        
        .line-numbers {
            background: #f0f0f0;
            padding: 10px;
            border-right: 1px solid #ccc;
            text-align: right;
            user-select: none;
            font-family: monospace;
            height: 250px; /* Altura fija para el scroll */
            
        }
        textarea {
            height: 250px;
            resize: none;
            font-family: 'Courier New', Courier, monospace;
            width: 100%;
            padding: 10px;
            border: none;
            outline: none;
        }
        .status {
            margin-top: 10px;
            font-size: 0.9em;
        }
        .code-editor-container{
            overflow-x: auto;
            overflow-y: auto;
        }
        .editor-container {
            display: flex;
            overflow: hidden; /* Evitar que el contenido se desborde */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Registro de usuario</h1>
        <br>
        <form class="text-center" action="SvSignup" method="POST">
            <div class="editor-container" id="code-editor-container">
                <div class="line-numbers" id="lineNumbers"></div>
                <textarea name="signup" class="form-control" id="login" rows="15" placeholder="Código de registro" oninput="updateLineNumbers()" onkeyup="updateCursorPosition()"></textarea>
            </div>
            <div class="status" id="cursorPosition">Fila: 1  Columna: 1</div>
            <br>
            <button type="submit" class="btn btn-primary">Registrarse</button>
            <br>
            ó
            <br>
            <a href="index.jsp" class="btn btn-secondary">Iniciar sesion</a>
        </form>
    </div>

    <script>
        function updateLineNumbers() {
            const textarea = document.getElementById('login');
            const lineNumbers = document.getElementById('lineNumbers');
            const lines = textarea.value.split('\n').length;
            lineNumbers.innerHTML = '';
            for (let i = 1; i <= lines; i++) {
                lineNumbers.innerHTML += i + '<br>';
            }
        }

        function updateCursorPosition() {
            const textarea = document.getElementById('login');
            const cursorPosition = textarea.selectionStart;
            const value = textarea.value.substring(0, cursorPosition);
            const line = value.split('\n').length;
            const column = value.split('\n').pop().length + 1; // +1 para índice basado en 1
            document.getElementById('cursorPosition').innerText = `Fila: ${line}  Columna: ${column}`;
        }
    </script>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

