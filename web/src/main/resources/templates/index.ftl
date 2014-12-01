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
                <form role="form" method="post" action="/generate/springBoot.zip">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="form-group">
                                <label for="group">Group</label>
                                <input class="form-control" id="group" name="group" value="org.example">
                            </div>
                            <div class="form-group">
                                <label for="artifact">Artifact</label>
                                <input class="form-control" id="artifact" name="artifact" value="spring-boot-example">
                            </div>
                            <div class="form-group">
                                <label for="version">Version</label>
                                <input class="form-control" id="version" name="version" value="1.0-SNAPSHOT">
                            </div>
                            <div class="form-group">
                                <label for="bootVersion">Spring Boot version</label>
                                <select class="form-control" name="bootVersion" id="bootVersion">
                                    <option>1.1.9.RELEASE</option>
                                    <option>1.2.0.RC2</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="language">Language</label>
                                <select class="form-control" name="language" id="language">
                                    <option>java</option>
                                    <option>groovy</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <h4>Шаблонизаторы</h4>
                            <div class="form-group">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="useFreemarker">
                                        Freemarker
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="useVelocity">
                                        Velocity
                                    </label>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <button class="btn btn-info pull-right btn-lg" type="submit">
                                Сгенерировать проект
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>