package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void setCarToUser(User user, Car car) {
        Session session = sessionFactory.getCurrentSession();
        User updatedUser = session.get(User.class, user.getId());
        updatedUser.setCar(car);
        session.save(updatedUser);

    }

    @Override
    public User getUser(long Userid) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where id=:id")
                .setParameter("id", Userid);
        return (User) query.getSingleResult();
    }

    @Override
    public User getUserByCar(String model, int series) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User where car.model=:model and car.series=:series")
                .setParameter("model", model)
                .setParameter("series", series);
        return (User) query.getSingleResult();
    }


}
