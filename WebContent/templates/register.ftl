<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="utf-8">
        <title>Netbooks</title>

    <!-- favicon-->
        <link rel="icon" href="http://www.iconsdb.com/icons/preview/green/book-xxl.png">
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="regsuccess.js"></script>
        
        <!-- Navbar color override -->
        <link rel="stylesheet" href="css/bootstrap.css">
</head>
    <body  style="background:#0e0f11">
        <!--Nav bar -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Netbooks</a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"> Not Logged In
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
     <div class="container" style="margin-top:90px">

        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Sign Up</h3>
            </div>
            <div class="panel-body">
                <form role="form" action="Register" method="post">
                    <div class="row">
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="form-group">
                                <input type="text" name="first_name" id="first_name" class="form-control" placeholder="First Name" required>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="form-group">
                                <input type="text" name="last_name" id="last_name" class="form-control " placeholder="Last Name" required>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="form-group">
                                <input placeholder="Birthdate" name="birth_date" class="form-control" type="text" onfocus="(this.type='date')" onblur="(this.type='text')" id="date">
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-group">
                <input type="text" name="address" class="form-control" id="address" placeholder="Street Address" required>
            </div>
                    
            <div class="row">
                <div class="col-xs-12 col-sm-4 col-md-4">
                    <div class="form-group">
                        <input type="text" name = "city" class="form-control" id="city" placeholder="City" required>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-4 col-md-4">
                    <div class="form-group">
                        <select class="form-control" id="state" name="state">
                            <option value="" disabled selected>State</option>
                            <option value="AK">Alaska</option>
                            <option value="AL">Alabama</option>
                            <option value="AR">Arkansas</option>
                            <option value="AZ">Arizona</option>
                            <option value="CA">California</option>
                            <option value="CO">Colorado</option>
                            <option value="CT">Connecticut</option>
                            <option value="DC">District of Columbia</option>
                            <option value="DE">Delaware</option>
                            <option value="FL">Florida</option>
                            <option value="GA">Georgia</option>
                            <option value="HI">Hawaii</option>
                            <option value="IA">Iowa</option>
                            <option value="ID">Idaho</option>
                            <option value="IL">Illinois</option>
                            <option value="IN">Indiana</option>
                            <option value="KS">Kansas</option>
                            <option value="KY">Kentucky</option>
                            <option value="LA">Louisiana</option>
                            <option value="MA">Massachusetts</option>
                            <option value="MD">Maryland</option>
                            <option value="ME">Maine</option>
                            <option value="MI">Michigan</option>
                            <option value="MN">Minnesota</option>
                            <option value="MO">Missouri</option>
                            <option value="MS">Mississippi</option>
                            <option value="MT">Montana</option>
                            <option value="NC">North Carolina</option>
                            <option value="ND">North Dakota</option>
                            <option value="NE">Nebraska</option>
                            <option value="NH">New Hampshire</option>
                            <option value="NJ">New Jersey</option>
                            <option value="NM">New Mexico</option>
                            <option value="NV">Nevada</option>
                            <option value="NY">New York</option>
                            <option value="OH">Ohio</option>
                            <option value="OK">Oklahoma</option>
                            <option value="OR">Oregon</option>
                            <option value="PA">Pennsylvania</option>
                            <option value="RI">Rhode Island</option>
                            <option value="SC">South Carolina</option>
                            <option value="SD">South Dakota</option>
                            <option value="TN">Tennessee</option>
                            <option value="TX">Texas</option>
                            <option value="UT">Utah</option>
                            <option value="VA">Virginia</option>
                            <option value="VT">Vermont</option>
                            <option value="WA">Washington</option>
                            <option value="WI">Wisconsin</option>
                            <option value="WV">West Virginia</option>
                            <option value="WY">Wyoming</option>
                        </select>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-4 col-md-4">
                    <div class="form-group">
                        <input type="number" name = "zip" class="form-control" id="zip" placeholder="Zip Code" min="11111" max="99999" required>
                    </div>
                </div>
                    </div>
                    <div class="form-group">
                        <input type="text" name="display_name" id="display_name" class="form-control " placeholder="Username" pattern=".{6,}" title="Six or more characters" required>
                    </div>
                    <div class="form-group">
                        <input type="email" name="email" id="email" class="form-control " placeholder="Email Address" required>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-6 col-md-6">
                            <div class="form-group">
                                <input type="password" name="password" id="password" class="form-control " placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must contain 8 or more characters, one number, and one uppercase letter' : ''); if(this.checkValidity()) form.password2.pattern = this.value;" required>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-6">
                            <div class="form-group">
                                <input type="password" name="password2" id="password2" class="form-control " placeholder="Confirm Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same password as above' : '');" required>
                            </div>
                        </div>
                    </div>
                    <div class="input-group">
                      <div style="margin-top: 0px;">
                        <label class="radio-inline">
                          <input type="radio" value="base" name="optradio">Base Subscription
                        </label>
                        <label class="radio-inline">
                          <input type="radio" name="optradio">Premium Subscription
                        </label>
                      </div>
                    </div>
                    <hr style="margin-top:10px;margin-bottom:10px;" >
                    Username taken!<br/>
                    <button type="submit" id="reg" class="btn btn-success">Register</button>
                    <a href="index.html" role=button class="btn btn-danger">Cancel</a>
                </form>
            </div>
        </div>
    </div>
    </div>
    </body>
</html>