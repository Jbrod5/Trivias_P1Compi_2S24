/* - - - - - - - - - - - - - - - - - CODIGO DE USUARIO - - - - - - - - - - - - - - - - - */

package com.jbrod.servidorprincipal.analizadores;
import java_cup.runtime.*;


%%


/*  - - - - - - - - - - - - - - - - - DECLARACIONES  - - - - - - - - - - - - - - - - - */
%public 
%class Lexer
%cup
%line
%column

numero = [0-9]+

string = [a-zA-Z0-9\s]+
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
comill = "\""

encabezado = "<?xson version=”1.0” ?>"


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


%{

    private Symbol symbol(int type){
        return new Symbol(type, yyline +1, yycolumn +1);
    }

    private Symbol(type, value){
        System.out.println("Token reconocido: " + yytext());
        return new Symbol(type, yyline +1, yycolumn + 1, value);
    }

    private void error (String message){
        System.out.println("Error en la linea: " + (yyline +1) + " columna: " + (yycolumn +1) + " : " + message);
    }

%}


%%

/*  - - - - - - - - - - - - - - - - - REGLAS LEXICAS  - - - - - - - - - - - - - - - - - */

{numero}          { return symbol(sym.NUMERO       , Integer.parseInt(yytext())); }

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

{realizar_sol} { return symbol(sym.REALIZAR_SOL); }
{fin_sol_real} { return symbol(sym.FIN_SOL_REAL); }

{usuario_nuev} { return symbol(sym.USUARIO_NUEV); }    
{datos_usuari} { return symbol(sym.DATOS_USUARI); }    
{usuario}      { return symbol(sym.USUARIO);      }   
{password}     { return symbol(sym.PASSWORD);     }   
{nombre}       { return symbol(sym.NOMBRE);       }   
{institucion}  { return symbol(sym.INSTITUCION);  }  

{modi_usuario} { return symbol(sym.MODI_USUARIO); }
{usua_antiguo} { return symbol(sym.USUA_ANTIGUO); }
{nuevo_passwo} { return symbol(sym.NUEVO_PASSWO); }

{eliminar_usu} { return symbol(sym.ELIMINAR_USU); }

{login_usuari} { return symbol(sym.LOGIN_USUARI); }

{nueva_trivia} { return symbol(sym.NUEVA_TRIVIA); } 
{param_trivia} { return symbol(sym.PARAM_TRIVIA); } 
{id_trivia}    { return symbol(sym.ID_TRIVIA);    }
{tiempo_pregu} { return symbol(sym.TIEMPO_PREGU); }
{nombre}       { return symbol(sym.NOMBRE);       } 
{tema}         { return symbol(sym.TEMA);         }

{eliminar_tri} { return symbol(sym.ELIMINAR_TRI); }

{modif_trivia}   { return symbol(sym.MODIF_TRIVIA); }

{agregar_comp}   { return symbol(sym.AGREGAR_COMP); }
{id}             { return symbol(sym.ID);           }  
{trivia}         { return symbol(sym.TRIVIA);       }   
{respuesta}      { return symbol(sym.RESPUESTA);    }    
{clase}          { return symbol(sym.CLASE);        }    
{texto_visibl}   { return symbol(sym.TEXTO_VISIBL); }   
{opciones}       { return symbol(sym.OPCIONES);     }   
{indice}         { return symbol(sym.INDICE);       }   
{filas}          { return symbol(sym.FILAS);        } 
{columnas}       { return symbol(sym.COLUMNAS);     }   

{encabezado}      { return symbol(sym.ENCABEZADO); }
{identificador}   { return symbol(sym.IDENTIFICADOR, yytexyt());                  }
{string}          { return symbol(sym.STRING, yytext());                          }


[^]            { System.out.println("No se reconocio el lexema " + yytext() + " como un token valido y se ignoro.");
                 errores.agregarError(yytext(), yyline +1, yycolumn + 1, "Lexico", "El simbolo no se encuentra definido en el alfabeto.");}
<<EOF>>        { return symbol(sym.EOF); }