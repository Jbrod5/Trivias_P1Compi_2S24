<%-- 
    Document   : solicitudes
    Created on : 25/09/2024, 01:23:48
    Author     : jorge
--%>

<%-- 
    Document   : solicitudes
    Created on : 25/09/2024, 01:23:48
    Author     : jorge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editor de Código Simulado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #editor-container {
            height: 80vh;
            overflow-y: auto;
            border: 1px solid #ccc;
        }
        #line-numbers {
            width: 50px;
            text-align: right;
            padding-right: 5px;
            border-right: 1px solid #ccc;
            background-color: #f0f0f0;
            user-select: none;
            font-size: 12px;
            padding: 0 10px;
        }
        #code-area {
            flex-grow: 1;
            font-family: monospace;
            resize: none;
            border: none;
            outline: none;
            padding: 0 10px;
            font-size: 12px;
        }
        #right-box {
            width: 25%;
            height: 80vh;
            overflow-y: auto;
            border: 1px solid #ccc;
            resize: none;
            font-family: monospace;
        }
    </style>
</head>
<body>
    <form action="SvSolicitud" method="POST">
    <div class="container mt-4">
        <div class="d-flex">
            <div id="editor-container" class="d-flex flex-grow-1">
                <pre id="line-numbers"></pre>
                <textarea name="codigo" id="code-area" class="form-control" rows="20">
                <%= request.getAttribute("codigo") != null ? request.getAttribute("codigo") : "" %>
                </textarea>
            </div>
            <textarea name="respueta" id="right-box" class="form-control ms-3">
                <%= request.getAttribute("respuesta") != null ? request.getAttribute("respuesta") : "" %>
            </textarea>
        </div>
        <button id="procesar" type="submit" class="btn btn-primary mt-3">Procesar</button>
    </div>
    </form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const codeArea = document.getElementById('code-area');
        const lineNumbers = document.getElementById('line-numbers');

        function updateLineNumbers() {
            const lines = codeArea.value.split('\n').length;
            lineNumbers.innerHTML = Array(lines).fill().map((_, i) => i + 1).join('\n');
        }

        codeArea.addEventListener('input', updateLineNumbers);
        codeArea.addEventListener('scroll', () => {
            lineNumbers.scrollTop = codeArea.scrollTop;
        });

        updateLineNumbers();
    </script>
</body>
</html>
