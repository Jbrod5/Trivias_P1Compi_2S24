/* - - - - - - - - - - - - - - - - - CODIGO DE USUARIO - - - - - - - - - - - - - - - - - */

import java_cup.runtime.*;


%%


/*  - - - - - - - - - - - - - - - - - DECLARACIONES  - - - - - - - - - - - - - - - - - */
%public 
%class Lexer
%cup
%line
%column

numero = [0-9]+
identificador = [a-zA-Z_-$][a-zA-Z0-9_-$]*

string = [a-zA-Z0-9\s]+

menque = "<"
mayque = ">"
exclam = "!"
dospun = ":"
llavop = "{"
llavcl = "}"
coropn = "["
corcls = "]"
comma  = ","


realizar_sol = ("r"|"R")("e"|"E")("a"|"A")("l"|"L")("i"|"I")("z"|"Z")("a"|"A")("r"|"R")"_"("s"|"S")("o"|"O")("l"|"L")("i"|"I")("c"|"I")("t"|"T")("u"|"U")("d"|"D") 
fin_sol_real = ("f"|"F")("i"|"I")("n"|"N")"_"("s"|"S")("o"|"O")("l"|"L")("i"|"I")("c"|"I")("i"|"I")("t"|"T")("u"|"U")("d"|"D")"_"("r"|"R")("e"|"E")("a"|"A")("l"|"L")("i"|"I")("z"|"Z")("a"|"A")("d"|"D")("a"|"A")

usuario_nuev = ("u"|"U")("s"|"S")("u"|"U")("a"|"A")("r"|"R")("i"|"O")"_"("n"|"N")("u"|"U")("e"|"E")("v"|"V")("o"|"O")
datos_usuari = ("d"|"D")("a"|"A")("t"|"T")("o"|"O")("s"|"S")"_"("u"|"U")("s"|"S")("u"|"U")("a"|"A")("r"|"R")("i"|"I")("o"|"O")
usuario      = ("u"|"U")("s"|"S")("u"|"U")("a"|"A")("r"|"R")("i"|"I")("o"|"O")
password     = ("p"|"P")("a"|"A")("s"|"S")("s"|"S")("w"|"W")("o"|"O")("r"|"R")("d"|"D")
nombre       = ("n"|"N")("o"|"O")("m"|"M")("b"|"B")("r"|"R")("e"|"E")
institucion  = ("i"|"I")("n"|"N")("s"|"S")("t"|"T")("i"|"I")("t"|"T")("u"|"U")("c"|"C")("i"|"I")("o"|"O")("n"|"N")

modi_usuario = ("m"|"M")("o"|"O")("d"|"D")("i"|"I")("f"|"F")("i"|"I")("c"|"C")("a"|"A")("r"|"R")"_"("u"|"U")("s"|"S")("u"|"U")("a"|"A")("r"|"R")("i"|"I")("o"|"O")
usua_antiguo = ("u"|"U")("s"|"S")("u"|"U")("a"|"A")("r"|"R")("i"|"I")("o"|"O")"_"("a"|"A")("n"|"N")("t"|"T")("i"|"I")("g"|"G")("u"|"U")("o"|"O")
nuevo_passwo = ("n"|"N")("u"|"U")("e"|"E")("v"|"V")("o"|"O")"_"("p"|"P")("a"|"A")("s"|"S")("s"|"S")("w"|"W")("o"|"O")("r"|"R")("d"|"D")

eliminar_usu = ("e"|"E")("l"|"L")("i"|"I")("m"|"M")("i"|"I")("n"|"N")("a"|"A")("r"|"R")"_"("u"|"U")("s"|"S")("u"|"U")("a"|"A")("r"|"R")("i"|"I")("o"|"O")

login_usuari = ("l"|"L")("o"|"O")("g"|"G")("i"|"I")("n"|"N")"_"("u"|"U")("s"|"S")("u"|"U")("a"|"A")("r"|"R")("i"|"I")("o"|"O")

nueva_trivia = ("n"|"N")("u"|"U")("e"|"E")("v"|"V")("a"|"A")"_"("t"|"T")("r"|"R")("i"|"I")("v"|"V")("i"|"I")("a"|"A")
param_trivia = ("p"|"P")("a"|"A")("r"|"R")("a"|"A")("m"|"M")("e"|"E")("t"|"T")("r"|"R")("o"|"O")("s"|"S")"_"("t"|"T")("r"|"R")("i"|"I")("v"|"V")("i"|"I")("a"|"A")
id_trivia    = ("i"|"I")("d"|"D")"_"("t"|"T")("r"|"R")("i"|"I")("v"|"V")("i"|"I")("a"|"A")
tiempo_pregu = ("t"|"T")("i"|"I")("e"|"E")("m"|"M")("p"|"P")("o"|"O")"_"("p"|"P")("r"|"R")("e"|"E")("g"|"G")("u"|"U")("n"|"N")("t"|"T")("a"|"A")
nombre       = ("n"|"N")("o"|"O")("m"|"M")("b"|"B")("r"|"R")("e"|"E")
tema         = ("t"|"T")("e"|"E")("m"|"M")("a"|"A")

eliminar_tri = ("e"|"E")("l"|"L")("i"|"I")("m"|"M")("i"|"I")("n"|"N")("a"|"A")("r"|"R")"_"("t"|"T")("r"|"R")("i"|"I")("v"|"V")("i"|"I")("a"|"A")

modif_trivia = ("m"|"M")("o"|"O")("d"|"D")("i"|"I")("f"|"F")("i"|"I")("c"|"C")("a"|"A")("r"|"R")"_"("t"|"T")("r"|"R")("i"|"I")("v"|"V")("i"|"I")("a"|"A")

agregar_comp = ("a"|"A")("g"|"G")("r"|"R")("e"|"E")("g"|"G")("a"|"A")("r"|"R")"_"("c"|"C")("o"|"O")("m"|"M")("p"|"P")("o"|"O")("n"|"N")("e"|"E")("n"|"N")("t"|"T")("e"|"E")
id           = ("i"|"I")("d"|"D")
trivia       = ("t"|"T")("r"|"R")("i"|"I")("v"|"V")("i"|"I")("a"|"A")
respuesta    = ("r"|"R")("e"|"E")("s"|"S")("p"|"P")("u"|"U")("e"|"E")("s"|"S")("t"|"T")("a"|"A")
clase        = ("c"|"C")("l"|"L")("a"|"A")("s"|"S")("e"|"E")
texto_visibl = ("t"|"T")("e"|"E")("x"|"X")("t"|"T")("o"|"O")"_"("v"|"V")("i"|"I")("s"|"S")("i"|"I")("b"|"B")("l"|"L")("e"|"E")
opciones     = ("o"|"O")("p"|"P")("c"|"C")("i"|"I")("o"|"O")("n"|"N")("e"|"E")("s"|"S")
indice       = ("i"|"I")("n"|"N")("d"|"D")("i"|"I")("c"|"C")("e"|"E")
filas        = ("f"|"F")("i"|"I")("l"|"L")("a"|"A")("s"|"S")
columnas     = ("c"|"C")("o"|"O")("l"|"L")("u"|"U")("m"|"M")("n"|"N")("a"|"A")("s"|"S")
