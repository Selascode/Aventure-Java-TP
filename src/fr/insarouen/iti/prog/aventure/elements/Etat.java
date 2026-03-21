package fr.insarouen.iti.prog.aventure.elements;
/**
 * Énumération représentant l'état physique d'un élément ouvrable.
 * <p>
 * Cette énumération est utilisée pour définir le statut des objets activables
 * comme les portes. Elle permet de gérer la logique
 * d'ouverture et de fermeture (ex: on ne peut pas ouvrir une porte si son état
 * est {@code VERROUILLE}).
 * </p>
 */
public enum Etat {
    /**
     * L'élément est fermé mais n'est pas verrouillé.
     * Il peut être ouvert par le joueur.
     */
    FERME,
    /**
     * L'élément est ouvert.
     * Le passage est possible ou le contenu est accessible.
     */
    OUVERT,
    /**
     * L'élément est verrouillé à clé.
     * Il ne peut pas être ouvert sans être d'abord déverrouillé.
     */
    VERROUILLE,
    /**
     * L'élément a été déverrouillé.
     * Cet état est souvent transitoire et équivalent fonctionnellement à {@link #FERME}.
     */
    DEVERROUILLE}