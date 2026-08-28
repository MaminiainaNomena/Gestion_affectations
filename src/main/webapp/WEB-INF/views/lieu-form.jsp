<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lieu - Gestion des affectations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css?v=3">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css?v=3">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components.css?v=3">
  </head>
  <body>
    <%@ include file="notification.jspf" %>

    <header class="top-header">
      <%@ include file="page-select.jspf" %>
    </header>

    <div class="container">
      <main class="main-panel">
        <section class="page-heading">
          <div>
            <h1>${empty lieu ? 'Nouveau lieu' : 'Modifier lieu'}</h1>
            <p class="text-muted">Renseignez les informations du site.</p>
          </div>
        </section>

        <section class="panel form-panel">
          <form method="post" action="${pageContext.request.contextPath}/lieux">
            <input type="hidden" name="oldCode" value="${lieu.codelieu}">

            <div class="form-grid">
              <label class="field">
                Code lieu
                <input name="codelieu" maxlength="20" required value="${lieu.codelieu}">
              </label>

              <label class="field">
                Désignation
                <input name="designation" maxlength="120" required value="${lieu.designation}">
              </label>

              <label class="field full">
                Province
                <input name="province" maxlength="120" required value="${lieu.province}">
              </label>
            </div>

            <div class="form-actions">
              <button class="btn btn-primary" type="submit">Enregistrer</button>
              <a class="btn btn-outline" href="${pageContext.request.contextPath}/lieux">Annuler</a>
            </div>
          </form>
        </section>
      </main>
    </div>
  </body>
</html>