<!doctype html>
<html>
    <head>
        <title>Initializer by sala</title>
        <link rel="stylesheet" href="/webjars/bootstrap/3.3.1/css/bootstrap.css">
        <link rel="stylesheet" href="/webjars/bootstrap/3.3.1/css/bootstrap-theme.css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <h1>Initializer by sala</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <form role="form" method="post" action="/generate/springBoot.zip">
                        <div class="form-group">
                            <label for="version">Spring Boot version</label>
                            <select name="version" id="version">
                                <option>1.1.9.RELEASE</option>
                                <option>1.2.0.RC2</option>
                            </select>
                        </div>
                        <button class="btn btn-default" type="submit">
                            Сгенерировать проект
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>