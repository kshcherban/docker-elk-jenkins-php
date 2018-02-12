import javaposse.jobdsl.plugin.GlobalJobDslSecurityConfiguration
import jenkins.model.GlobalConfiguration
import jenkins.model.*
import hudson.security.*

GlobalConfiguration.all().get(GlobalJobDslSecurityConfiguration.class).useScriptSecurity=false

// Disable jenkins auth
def instance = Jenkins.getInstance()
def strategy = new hudson.security.AuthorizationStrategy.Unsecured()
instance.setAuthorizationStrategy(strategy)
def realm = new hudson.security.HudsonPrivateSecurityRealm(false, false, null)
instance.setSecurityRealm(realm)
instance.disableSecurity()
