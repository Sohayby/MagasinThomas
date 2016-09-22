/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import magasin.entity.Categorie;
import magasin.entity.Client;
import magasin.entity.Commande;
import magasin.entity.Produit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.internal.runners.statements.Fail;

/**
 *
 * @author tom
 */
public class MonTest {

    @Before
    public void avant() {
        // Vide toutes les tables
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM Commande c").executeUpdate();
        em.createQuery("DELETE FROM Client c").executeUpdate();
        em.createQuery("DELETE FROM Produit p").executeUpdate();
        em.createQuery("DELETE FROM Categorie p").executeUpdate();

        Client i1 = new Client();
        i1.setId(1L);
        i1.setNom("Riri");
        em.persist(i1);

        Client i2 = new Client();
        i2.setId(2L);
        i2.setNom("Fifi");
        em.persist(i2);

        Client i3 = new Client();
        i3.setId(3L);
        i3.setNom("Loulou");
        em.persist(i3);

        Commande cmd1 = new Commande();
        cmd1.setId(1L);
        cmd1.setClient(i1);
        cmd1.setPrixTotal(1000);
        i1.getCommandes().add(cmd1);
        em.persist(cmd1);

        Commande cmd2 = new Commande();
        cmd2.setId(2L);
        cmd2.setClient(i3);
        i3.getCommandes().add(cmd2);
        cmd2.setPrixTotal(5);
        
        em.persist(cmd2);

        Commande cmd3 = new Commande();
        cmd3.setId(3L);
        cmd3.setClient(i3);
        cmd3.setPrixTotal(2);
        i3.getCommandes().add(cmd3);
        em.persist(cmd3);

        em.getTransaction().commit();

    }

    @Test
    public void verifieLoulou2Cmd() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Client c = em.find(Client.class, 3L);System.out.println(c.getCommandes().size());
        if (c.getCommandes().size() != 2) {
            Assert.fail();
        }

    }

    @Test
    public void verifQueCmd3PasseeParLolou() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        Commande cmd3 =  em.find(Commande.class, 3L);
        Assert.assertEquals("Loulou", cmd3.getClient().getNom());
      

    }
     @Test
     public void verifQueCmd2PasseParRiri() {
          EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
          Commande cmd2=em.find(Commande.class, 2L);
          Assert.assertNotEquals("Riri", cmd2.getClient().getNom());
     }
//        
//        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
//        
//        Categorie cat = em.find(Categorie.class, 1L);
//        
//        if (cat.getNom().equals("Basket") == false) {
//            Assert.fail("CA MARCHE PAS MON GARS!");
//        }
//    }
//    
//    @Test
//    public void testListeProdCategorie() {
//        
//        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
//        
//        Categorie cat = em.find(Categorie.class, 1L);
//        for (Produit p : cat.getProduits()) {
//            
//            System.out.println(p);
//        }
//    }
//    
//    @Test
//    public void testCreateDB() {
//        
//    }
//    
}
