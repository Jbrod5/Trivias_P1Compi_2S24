/* - - - - - - - - - - - - - - - - - CODIGO DE USUARIO - - - - - - - - - - - - - - - - - */


package com.jbrod.servidorprincipal.analizadores.carga.puntuaciones;

import java_cup.runtime.*;

%%

/*  - - - - - - - - - - - - - - - - - DECLARACIONES  - - - - - - - - - - - - - - - - - */

%public 
%class LexerPuntuaciones
%cup
%line
%column

numero = [0-9]+
string = [a-zA-Z0-9\¿][a-zA-Z0-9\¿\?\s]*
//string = [a-zA-Z0-9\¿][a-zA-Z0-9:\¿\?\s]*
identificador = ([a-zA-Z]|"_"|"-"|"$")([a-zA-Z0-9]|"_"|"-"|"$")*

menque = "<"
mayque = ">"
exclam = "!"
dospun = ":"
llavop = "{"
llavcl = "}"
coropn = "["
corcls = "]"
paropn = "("
parcls = ")" 
comma  = ","
qstmrk = "?"
comill = "\""|"“"|"”"
orsymb = "|" 
encabezado = "<BASE_DE_DATOS>"
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f\s]


puntuaciones = "puntuaciones"
puntuacion_t = "PUNTUACION_TRIVIA"
usuario  = "USUARIO"
tiempo = "TIEMPO"
punteo       = "PUNTEO"



%{
    private Symbol symbol(int type){
        return new Symbol(type, yyline +1, yycolumn +1);
    }

    private Symbol symbol(int type, Object value){
        System.out.println("Token con valor reconocido: " + yytext());
        return new Symbol(type, yyline +1, yycolumn + 1, value);
    }

    private void error (String message){
        System.out.println("Error en la linea: " + (yyline +1) + " columna: " + (yycolumn +1) + " : " + message);
    }
%}

%%

/*  - - - - - - - - - - - - - - - - - REGLAS LEXICAS  - - - - - - - - - - - - - - - - - */

{puntuaciones} { return symbol(sym.PUNTUACIONES); }
{puntuacion_t} { return symbol(sym.PUNTUACION_T); }
{usuario}  { return symbol(sym.USUARIO);  }
{tiempo}        { return symbol(sym.TIEMPO); }
{punteo}       { return symbol(sym.PUNTEO); }


{menque}          { return symbol(sym.MENQUE); }          
{mayque}          { return symbol(sym.MAYQUE); }            
{exclam}          { return symbol(sym.EXCLAM); }            
{dospun}          { return symbol(sym.DOSPUN); }                        
{llavop}          { return symbol(sym.LLAVOP); }            
{llavcl}          { return symbol(sym.LLAVCL); }            
{coropn}          { return symbol(sym.COROPN); }            
{corcls}          { return symbol(sym.CORCLS); }            
{paropn}          { return symbol(sym.PAROPN); }
{parcls}          { return symbol(sym.PARCLS); } 
{comma}           { return symbol(sym.COMMA);  }
{qstmrk}          { return symbol(sym.QSTMRK); }
{comill}          { return symbol(sym.COMILL); }
{orsymb}          { return symbol(sym.ORSYMB); }

{encabezado}      { return symbol(sym.ENCABEZADO); }
{WhiteSpace}      { /* Ignorar */}
{identificador}   { return symbol(sym.IDENTIFICADOR, yytext());                   }
{string}          { return symbol(sym.STRING, yytext());                          }
{numero}          { return symbol(sym.NUMERO       , Integer.parseInt(yytext())); }

[^]            { System.out.println("No se reconocio el lexema " + yytext() + " como un token valido y se ignoro.");
                 //errores.agregarError(yytext(), yyline +1, yycolumn + 1, "Lexico", "El simbolo no se encuentra definido en el alfabeto.");
                 }
<<EOF>>        { return symbol(sym.EOF); }
