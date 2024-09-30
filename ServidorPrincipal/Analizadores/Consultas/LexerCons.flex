/* - - - - - - - - - - - - - - - - - CODIGO DE USUARIO - - - - - - - - - - - - - - - - - */

package com.jbrod.servidorprincipal.analizadores.consultas;
import java_cup.runtime.*;


%%


/*  - - - - - - - - - - - - - - - - - DECLARACIONES  - - - - - - - - - - - - - - - - - */
%public 
%class LexerCons
%cup
%line
%column

numero = ([0-9]+|[0-9]+\.[0-9]+)
identificador = ([a-zA-Z]|"_"|"-"|"$")([a-zA-Z0-9]|"_"|"-"|"$")*

menque = "<"
mayque = ">"
exclam = "!"
dospun = ":"
llavop = "{"
llavcl = "}"
coropn = "["
corcls = "]"
comma  = ","
qstmrk = "?"
comill = "\""|"“"|"”"|"´"|"'"|"‘"|"’"
orsymb = "|"
equals = "=" 

selec_reporte = "SELECCIONAR REPORTE"
filtrar       = "FILTRAR POR"

/* Campos */
tiempo  = "TIEMPO"
usuario = "USUARIO"
puntuac = "PUNTUACION"

/* Operadores */
and = "AND"
or  = "OR"

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f\s]




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

{WhiteSpace}      { /* Ignorar */}

{menque}          { return symbol(sym.MENQUE); }          
{mayque}          { return symbol(sym.MAYQUE); }            
{exclam}          { return symbol(sym.EXCLAM); }            
{dospun}          { return symbol(sym.DOSPUN); }                        
{llavop}          { return symbol(sym.LLAVOP); }            
{llavcl}          { return symbol(sym.LLAVCL); }            
{coropn}          { return symbol(sym.COROPN); }            
{corcls}          { return symbol(sym.CORCLS); }            
{comma}           { return symbol(sym.COMMA);  }
{qstmrk}          { return symbol(sym.QSTMRK); }
{comill}          { return symbol(sym.COMILL); }
{orsymb}          { return symbol(sym.ORSYMB); }
{equals}          { return symbol(sym.EQUALS); }

{and} { return symbol(sym.AND); }
{or}  { return symbol(sym.OR); }

{selec_reporte} { return symbol(sym.SELEC_REPORTE); }
{filtrar}       { return symbol(sym.FILTRAR)      ; }
{tiempo}        { return symbol(sym.TIEMPO)       ; }
{usuario}       { return symbol(sym.USUARIO)      ; }
{puntuac}       { return symbol(sym.PUNTUAC)       ; }

{identificador}   { return symbol(sym.IDENTIFICADOR, yytext());                   }
{numero}          { return symbol(sym.NUMERO       , Integer.parseInt(yytext())); }


[^]            { System.out.println("No se reconocio el lexema " + yytext() + " como un token valido y se ignoro.");
                 //errores.agregarError(yytext(), yyline +1, yycolumn + 1, "Lexico", "El simbolo no se encuentra definido en el alfabeto.");
                 }
<<EOF>>        { return symbol(sym.EOF); }  