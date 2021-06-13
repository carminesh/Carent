<!--LRegistration section-->
<div class="registration-window">
    <div class="container justify-content-center">
        <div class="row justify-content-center">
            <form id="form-registration" method="post" action="register">
                <div class="row justify-content-center" id="row-email">
                    <div class="row text-center">
                        <h2 id="title-window">Registrati</h2>
                    </div>
                    <div class="col-sm-10 col-md-6" id="email-input">
                        <label for="exampleInputEmail1" class="form-label">Indirizzo Email</label>
                        <input type="email" name="email" class="form-control" id="exampleInputEmail1"
                               aria-describedby="emailHelp" placeholder="Email" required>
                        <div id="emailHelp" class="form-text" style="font-size: 15px;">Non condivideremo mai la tua
                            email con terzi.
                        </div>
                    </div>
                </div>

                <div class="row justify-content-center" id="row-name">
                    <div class="col-sm-10 col-md-6" id="name-input">
                        <label for="exampleInputPassword1" class="form-label">Nome</label>
                        <input type="text" name="name" class="form-control"
                               id="user-name" placeholder="Nome" required>
                    </div>
                </div>

                <div class="row justify-content-center" id="row-surname">
                    <div class="col-sm-10 col-md-6" id="surname-input">
                        <label for="exampleInputPassword1" class="form-label">Cognome</label>
                        <input type="text" name="surname" class="form-control"
                               id="user-surname" placeholder="Cognome" required>
                    </div>
                </div>

                <div class="row justify-content-center" id="row-phonenumber">
                    <div class="col-sm-10 col-md-6" id="phonenumber-input">
                        <label for="exampleInputPassword1" class="form-label">Telefono</label>
                        <input type="tel" name="phonenumber" class="form-control"
                               id="user-phonenumber" placeholder="Telefono" required>
                    </div>
                </div>

                <div class="row justify-content-center" id="row-password">
                    <div class="col-sm-10 col-md-6" id="password-input">
                        <label for="exampleInputPassword1" class="form-label">Password</label>
                        <input type="password" name="passwd" class="form-control" id="user-password"
                               placeholder="Password" required>
                    </div>
                </div>

                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-success"
                            id="registration-button">Registrati
                    </button>
                    <div class="form-text" id="text-for-login">Sei già registrato? <a>Accedi</a></div>
                </div>
            </form>
        </div>
    </div>
</div>
