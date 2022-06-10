window.onload = function() {
            document.getElementById("login-btn").addEventListener("click", login);
            document.getElementById("register-btn").addEventListener("click", register);
                }


        function login() {
            let username = document.getElementById("username").value;
            let password = document.getElementById("password").value;
            console.log(username, password);
            fetch('/basketball/auth/login', {
                method: 'POST',
                body: JSON.stringify({username, password})
            }).then(function () {
                console.log('login success!')
            }).catch(() => {
                console.log("Invalid login credentials.")
            });
            }

        function register() {
                    let firstName = document.getElementById("firstName").value;
                    let lastName = document.getElementById("lastName").value;
                    let username = document.getElementById("newUsername").value;
                    let password = document.getElementById("newPassword").value;
                    let position = document.getElementById("position").value;
                    console.log(firstName, lastName, username, password, position);
                    fetch('/basketball/auth/register', {
                        method: 'POST',
                        body: JSON.stringify({firstName, lastName, username, password, position})
                    }).then(function () {
                        console.log('Registration successful!')
                    }).catch(() => {
                        console.log("Invalid registration credentials.")
                    });
        }






