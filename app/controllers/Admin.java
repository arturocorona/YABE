package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

/**
 * Hemos creado el área de administracion usando el módulo CRUD, pero todavía no
 * está bien integrada con la interfaz de usuario blog. Por lo que empezaremos a
 * trabajar en una nueva área de administración, la cual dará a cada autor
 * acceso a sus propias publicaciones (posts). Todas las funciones CRUD de
 * administración seguirán disponibles para los usuarios con perfil (profile) de
 * super administrador.
 *
 * @author arturo
 */
@With(Secure.class)
public class Admin extends Controller {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
        }
    }

    public static void index() {
        render();
    }

}
