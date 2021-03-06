package br.edu.cefsa.compiladoresn2.conversor;

import br.edu.cefsa.compiladoresn2.tag.Tag;

public class ConversorXML {
     
    public static void escreveNode(String valor, StringBuilder stringFinal) {
        stringFinal.append(("\"" + valor + "\"" + ": {"));
    }

    public static void escreveArrayInicio(String valor, StringBuilder stringFinal) {
        stringFinal.append(("\"" + valor + "\"" + ": ["));
    }

    public static void escreveDado(Tag tagObj, StringBuilder stringFinal) {
        stringFinal.append("\"" + tagObj.getNome() + "\": \"" + tagObj.getValor() + "\"");
    }

    public static StringBuilder fazConversaoParaJSON() {
        StringBuilder stringToReturn = new StringBuilder();
        try {

            var allTags = Tag.organizer;
            stringToReturn.append('{');
            Integer next = 0;
            Tag paiAtual = new Tag();
            if (allTags != null) {
                for (Integer i = 0; i < allTags.size(); i++) {
                    next++;
                    Tag tagObj = allTags.get(i);
                    paiAtual = tagObj.getPai();
                    Tag proxTag = new Tag();
                    if (allTags.size() > next) {
                        proxTag = allTags.get(next);
                    }

                    if ("Node".equals(tagObj.getTipo())) {
                        paiAtual = tagObj;
                        stringToReturn.append("\n");
                        escreveNode(tagObj.getNome(), stringToReturn);
                    }

                    if ("ArrayInicio".equals(tagObj.getTipo())) {
                        paiAtual = tagObj;
                        stringToReturn.append("\n");
                        escreveArrayInicio(tagObj.getNome(), stringToReturn);
                        stringToReturn.append("\n" + "{");
                    }

                    if ("Dado".equals(tagObj.getTipo())) {
                        stringToReturn.append("\n");
                        escreveDado(tagObj, stringToReturn);
                        if (proxTag.getPai() == tagObj.getPai()) {
                            stringToReturn.append(",");
                        }
                    }
                    if ("Array".equals(tagObj.getTipo())) {
                        paiAtual = tagObj;
                        stringToReturn.append("\n" + "{");
                    }
                    if (proxTag.getPai() != paiAtual || proxTag.getNome() == null) {
                        if ("Array".equals(proxTag.getTipo())) {
                            stringToReturn.append("\n" + "},");
                        } else if ("Array".equals(paiAtual.getTipo()) && !"Array".equals(proxTag.getTipo()) && proxTag.getNome() != null) {
                            stringToReturn.append("\n" + "}" + "\n" + "],");
                        } else if ("Array".equals(paiAtual.getTipo()) && proxTag.getNome() == null) {
                            stringToReturn.append("\n" + "}" + "\n" + "]");
                        } else if ("Node".equals(paiAtual.getTipo()) && proxTag.getNome() == null) {
                            stringToReturn.append("\n" + "}");
                        } else if ("Node".equals(paiAtual.getTipo()) && proxTag.getNome() != null) {
                            stringToReturn.append("\n" + "},");
                        }
                        if (proxTag.getNome() == null && paiAtual.getPai() != null) {
                            Tag aux = new Tag();
                            paiAtual = paiAtual.getPai();
                            do {
                                if ("Array".equals(paiAtual.getTipo())) {
                                    stringToReturn.append("\n" + "}" + "\n" + "]");
                                } else if ("Node".equals(paiAtual.getTipo())) {
                                    stringToReturn.append("\n" + "}");
                                }
                                paiAtual = paiAtual.getPai();
                            } while (paiAtual != null);
                        }
                    }
                }
            }
            stringToReturn.append("\n" + "}");
            return stringToReturn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return stringToReturn;
    }
}
