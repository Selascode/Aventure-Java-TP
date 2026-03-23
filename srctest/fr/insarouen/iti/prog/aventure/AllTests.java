package fr.insarouen.iti.prog.aventure;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.iti.prog.aventure.elements.TestEntite;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.TestSerrure;
import fr.insarouen.iti.prog.aventure.elements.structure.TestPiece;
import fr.insarouen.iti.prog.aventure.elements.vivants.TestMonstre;
import fr.insarouen.iti.prog.aventure.elements.vivants.TestVivant;
import fr.insarouen.iti.prog.aventure.TestMonde; 
import fr.insarouen.iti.prog.aventure.elements.structure.TestPorte;


@RunWith(Suite.class)
@SuiteClasses({
    TestEntite.class,
    TestPiece.class,
    TestVivant.class,
    TestMonstre.class,
    TestMonde.class,
    TestPorte.class,
    TestSerrure.class
})
public class AllTests {}