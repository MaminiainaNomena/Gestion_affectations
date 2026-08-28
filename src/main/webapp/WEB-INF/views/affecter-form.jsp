<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Affectation - Gestion des affectations</title>
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
            <h1>${empty affecter ? 'Nouvelle affectation' : 'Modifier affectation'}</h1>
            <p class="text-muted">Associez un employé à un lieu et choisissez la date.</p>
          </div>
        </section>

        <section class="panel form-panel">
          <form method="post" action="${pageContext.request.contextPath}/affectations">
            <input type="hidden" name="oldCodeemp" value="${affecter.id.codeemp}">
            <input type="hidden" name="oldCodelieu" value="${affecter.id.codelieu}">

            <div class="form-grid">
              <label class="field">
                Employé
                <select name="codeemp" required>
                  <c:forEach var="e" items="${employes}">
                    <option value="${e.codeemp}" ${affecter.employe.codeemp == e.codeemp ? 'selected' : ''}>
                      ${e.nom} ${e.prenom}
                    </option>
                  </c:forEach>
                </select>
              </label>

              <label class="field">
                Lieu
                <select name="codelieu" required>
                  <c:forEach var="l" items="${lieux}">
                    <option value="${l.codelieu}" ${affecter.lieu.codelieu == l.codelieu ? 'selected' : ''}>
                      ${l.designation}
                    </option>
                  </c:forEach>
                </select>
              </label>

              <label class="field">
                Date
                <input type="date" name="date" required value="${affecter.date}">
              </label>
            </div>

            <div class="form-actions">
              <button class="btn btn-primary" type="submit">Enregistrer</button>
              <a class="btn btn-outline" href="${pageContext.request.contextPath}/affectations">Annuler</a>
            </div>
          </form>
        </section>
      </main>
    </div>
  </body>
</html>