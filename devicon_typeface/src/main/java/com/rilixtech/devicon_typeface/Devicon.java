package com.rilixtech.devicon_typeface;

import ohos.agp.text.Font;
import ohos.app.AbilityContext;
import com.rilixtech.materialfancybutton.typeface.IIcon;
import com.rilixtech.materialfancybutton.typeface.ITypeface;
import com.rilixtech.materialfancybutton.utils.FontUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * ITypeface implementation using the Devicon font. It hosts a variety of icons that can be used by
 * the MaterialFancyButton Components.
 */
public class Devicon implements ITypeface {
    private static final String TTF_FILE = "devicon-font-v2.0.0.1.ttf";
    private static final String MAPPING_FONT_PREFIX = "DEVI";

    private static Font typeface = null;
    private static HashMap<String, Character> mChars;

    @Override public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    @Override public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> characterHashMap = new HashMap<>();
            for (Icon v : Icon.values()) {
                characterHashMap.put(v.name(), v.character);
            }
            setChars(characterHashMap);
        }
        return mChars;
    }

    private static void setChars(HashMap<String, Character> characterHashMap) {
        mChars = characterHashMap;
    }

    @Override
    public String getMappingPrefix() {
        return MAPPING_FONT_PREFIX;
    }

    @Override
    public String getFontName() {
        return "DevIcon";
    }

    @Override
    public String getVersion() {
        return "2" + ".0.0.1";
    }

    @Override
    public int getIconCount() {
        return mChars.size();
    }

    @Override
    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<>();
        for (Icon value : Icon.values()) {
            icons.add(value.name());
        }
        return icons;
    }

    @Override
    public String getAuthor() {
        return "Konpa";
    }

    @Override
    public String getUrl() {
        return "http://devicon.fr/";
    }

    @Override
    public String getDescription() {
        return "Devicon is a set of icons representing programming languages, designing & development tools. "
                + "You can use it as a font or directly copy/paste the svg code into your project.";
    }

    @Override
    public String getLicense() {
        return "MIT License";
    }

    @Override
    public String getLicenseUrl() {
        return "https://github.com/konpa/devicon/blob/master/LICENSE";
    }




    @Override
    public Font getTypeface(AbilityContext context) {
        if (typeface == null) {
            try {
                cacheTypeface(FontUtil.getFontFromRawFile(context, TTF_FILE));
            } catch (IllegalStateException e) {
                throw new IllegalStateException(e);
            }
        }
        return typeface;
    }

    private static void cacheTypeface(Font font) {
        typeface = font;
    }

    /**
     * Enumerates all the supported Custom Icon Unicode characters by this ITypeface.
     */
    public enum Icon implements IIcon {
        DEVI_SSH_PLAIN_WORDMARK('\ue900'),
        DEVI_SSH_PLAIN('\ue901'),
        DEVI_SOURCETREE_PLAIN_WORDMARK('\ue902'),
        DEVI_SOURCETREE_PLAIN('\ue903'),
        DEVI_PHPSTORM_PLAIN_WORDMARK('\ue904'),
        DEVI_PHPSTORM_PLAIN('\ue905'),
        DEVI_JEET_PLAIN_WORDMARK('\ue906'),
        DEVI_JEET_PLAIN('\ue907'),
        DEVI_GITLAB_PLAIN_WORDMARK('\ue908'),
        DEVI_GITLAB_PLAIN('\ue909'),
        DEVI_GITHUB_PLAIN_WORDMARK('\ue90a'),
        DEVI_GITHUB_PLAIN('\ue90b'),
        DEVI_D3JS_PLAIN('\ue90c'),
        DEVI_CONFLUENCE_PLAIN_WORDMARK('\ue90d'),
        DEVI_CONFLUENCE_PLAIN('\ue90e'),
        DEVI_BITBUCKET_PLAIN_WORDMARK('\ue90f'),
        DEVI_BITBUCKET_PLAIN('\ue910'),
        DEVI_SAFARI_LINE_WORDMARK('\ue632'),
        DEVI_SAFARI_LINE('\ue63a'),
        DEVI_SAFARI_PLAIN_WORDMARK('\ue63b'),
        DEVI_SAFARI_PLAIN('\ue63c'),
        DEVI_JETBRAINS_PLAIN_WORDMARK('\ue63d'),
        DEVI_DJANGO_LINE_WORDMARK('\ue63e'),
        DEVI_DJANGO_PLAIN_WORDMARK('\ue63f'),
        DEVI_GIMP_PLAIN('\ue633'),
        DEVI_REDHAT_PLAIN_WORDMARK('\ue62a'),
        DEVI_REDHAT_PLAIN('\ue62b'),
        DEVI_CPLUSPLUS_LINE_WORDMARK('\ue634'),
        DEVI_CPLUSPLUS_PLAIN_WORDMARK('\ue635'),
        DEVI_CSHARP_LINE_WORDMARK('\ue636'),
        DEVI_CSHARP_PLAIN_WORDMARK('\ue637'),
        DEVI_C_LINE_WORDMARK('\ue638'),
        DEVI_C_PLAIN_WORDMARK('\ue639'),
        DEVI_NODEWEBKIT_LINE_WORDMARK('\ue611'),
        DEVI_NODEWEBKIT_LINE('\ue612'),
        DEVI_NODEWEBKIT_PLAIN_WORDMARK('\ue613'),
        DEVI_NODEWEBKIT_PLAIN('\ue614'),
        DEVI_NGINX_PLAIN_WORDMARK('\ue615'),
        DEVI_ERLANG_PLAIN_WORDMARK('\ue616'),
        DEVI_ERLANG_PLAIN('\ue617'),
        DEVI_DOCTRINE_LINE_WORDMARK('\ue618'),
        DEVI_DOCTRINE_LINE('\ue619'),
        DEVI_DOCTRINE_PLAIN_WORDMARK('\ue61a'),
        DEVI_DOCTRINE_PLAIN('\ue625'),
        DEVI_APACHE_LINE_WORDMARK('\ue626'),
        DEVI_APACHE_LINE('\ue627'),
        DEVI_APACHE_PLAIN_WORDMARK('\ue628'),
        DEVI_APACHE_PLAIN('\ue629'),
        DEVI_GO_LINE('\ue610'),
        DEVI_REDIS_PLAIN_WORDMARK('\ue606'),
        DEVI_REDIS_PLAIN('\ue607'),
        DEVI_METEOR_PLAIN_WORDMARK('\ue608'),
        DEVI_METEOR_PLAIN('\ue609'),
        DEVI_HEROKU_ORIGINAL_WORDMARK('\ue60a'),
        DEVI_HEROKU_ORIGINAL('\ue60b'),
        DEVI_HEROKU_PLAIN_WORDMARK('\ue60c'),
        DEVI_HEROKU_PLAIN('\ue60f'),
        DEVI_GO_PLAIN('\ue61b'),
        DEVI_DOCKER_PLAIN_WORDMARK('\ue61e'),
        DEVI_DOCKER_PLAIN('\ue61f'),
        DEVI_AMAZONWEBSERVICES_PLAIN('\ue603'),
        DEVI_AMAZONWEBSERVICES_PLAIN_WORDMARK('\ue604'),
        DEVI_ANDROID_PLAIN_WORDMARK('\ue60d'),
        DEVI_ANDROID_PLAIN('\ue60e'),
        DEVI_ANGULARJS_PLAIN_WORDMARK('\ue61c'),
        DEVI_ANGULARJS_PLAIN('\ue61d'),
        DEVI_APPCELERATOR_PLAIN('\ue620'),
        DEVI_APPCELERATOR_PLAIN_WORDMARK('\ue621'),
        DEVI_APPLE_PLAIN('\ue622'),
        DEVI_ATOM_PLAIN_WORDMARK('\ue623'),
        DEVI_ATOM_PLAIN('\ue624'),
        DEVI_BACKBONEJS_PLAIN_WORDMARK('\ue62c'),
        DEVI_BACKBONEJS_PLAIN('\ue62d'),
        DEVI_BOOTSTRAP_PLAIN_WORDMARK('\ue62e'),
        DEVI_BOOTSTRAP_PLAIN('\ue62f'),
        DEVI_BOWER_LINE_WORDMARK('\ue630'),
        DEVI_BOWER_LINE('\ue631'),
        DEVI_BOWER_PLAIN_WORDMARK('\ue64e'),
        DEVI_BOWER_PLAIN('\ue64f'),
        DEVI_CHROME_PLAIN_WORDMARK('\ue665'),
        DEVI_CHROME_PLAIN('\ue666'),
        DEVI_CODEIGNITER_PLAIN_WORDMARK('\ue667'),
        DEVI_CODEIGNITER_PLAIN('\ue668'),
        DEVI_COFFEESCRIPT_PLAIN_WORDMARK('\ue669'),
        DEVI_COFFEESCRIPT_PLAIN('\ue66a'),
        DEVI_CSS3_PLAIN_WORDMARK('\ue678'),
        DEVI_CSS3_PLAIN('\ue679'),
        DEVI_DEBIAN_PLAIN_WORDMARK('\ue67e'),
        DEVI_DEBIAN_PLAIN('\ue67f'),
        DEVI_DOT_NET_PLAIN_WORDMARK('\ue6d3'),
        DEVI_DOT_NET_PLAIN('\ue6d4'),
        DEVI_DRUPAL_PLAIN_WORDMARK('\ue6e2'),
        DEVI_DRUPAL_PLAIN('\ue6e3'),
        DEVI_FIREFOX_PLAIN_WORDMARK('\ue75d'),
        DEVI_FIREFOX_PLAIN('\ue75e'),
        DEVI_FOUNDATION_PLAIN_WORDMARK('\ue7a2'),
        DEVI_FOUNDATION_PLAIN('\ue7a3'),
        DEVI_GIT_PLAIN_WORDMARK('\ue7a7'),
        DEVI_GIT_PLAIN('\ue7a8'),
        DEVI_GRUNT_LINE_WORDMARK('\ue7a9'),
        DEVI_GRUNT_LINE('\ue7aa'),
        DEVI_GRUNT_PLAIN_WORDMARK('\ue7ea'),
        DEVI_GRUNT_PLAIN('\ue7eb'),
        DEVI_GULP_PLAIN('\ue7ec'),
        DEVI_HTML5_PLAIN_WORDMARK('\ue7f6'),
        DEVI_HTML5_PLAIN('\ue7f7'),
        DEVI_IE10_PLAIN('\ue7f8'),
        DEVI_ILLUSTRATOR_LINE('\ue7f9'),
        DEVI_ILLUSTRATOR_PLAIN('\ue7fa'),
        DEVI_INKSCAPE_PLAIN_WORDMARK('\ue834'),
        DEVI_INKSCAPE_PLAIN('\ue835'),
        DEVI_JAVA_PLAIN_WORDMARK('\ue841'),
        DEVI_JAVA_PLAIN('\ue842'),
        DEVI_JAVASCRIPT_PLAIN('\ue845'),
        DEVI_JQUERY_PLAIN_WORDMARK('\ue849'),
        DEVI_JQUERY_PLAIN('\ue84a'),
        DEVI_KRAKENJS_PLAIN_WORDMARK('\ue84f'),
        DEVI_KRAKENJS_PLAIN('\ue850'),
        DEVI_LARAVEL_PLAIN_WORDMARK('\ue851'),
        DEVI_LARAVEL_PLAIN('\ue852'),
        DEVI_LESS_PLAIN_WORDMARK('\ue853'),
        DEVI_LINUX_PLAIN('\ueb1c'),
        DEVI_MONGODB_PLAIN_WORDMARK('\ueb43'),
        DEVI_MONGODB_PLAIN('\ueb44'),
        DEVI_MOODLE_PLAIN_WORDMARK('\ueb5a'),
        DEVI_MOODLE_PLAIN('\ueb5b'),
        DEVI_MYSQL_PLAIN_WORDMARK('\ueb60'),
        DEVI_MYSQL_PLAIN('\ueb61'),
        DEVI_NODEJS_PLAIN_WORDMARK('\ueb69'),
        DEVI_NODEJS_PLAIN('\ueb6a'),
        DEVI_ORACLE_PLAIN('\ueb6b'),
        DEVI_PHOTOSHOP_LINE('\ueb6c'),
        DEVI_PHOTOSHOP_PLAIN('\ueb6d'),
        DEVI_PHP_PLAIN('\ueb71'),
        DEVI_POSTGRESQL_PLAIN_WORDMARK('\ueb7c'),
        DEVI_POSTGRESQL_PLAIN('\ueb7d'),
        DEVI_PYTHON_PLAIN_WORDMARK('\ueb88'),
        DEVI_PYTHON_PLAIN('\ueb89'),
        DEVI_RAILS_PLAIN_WORDMARK('\ueba2'),
        DEVI_RAILS_PLAIN('\ueba3'),
        DEVI_REACT_PLAIN_WORDMARK('\ue600'),
        DEVI_REACT_PLAIN('\ue601'),
        DEVI_RUBY_PLAIN_WORDMARK('\uebc9'),
        DEVI_RUBY_PLAIN('\uebca'),
        DEVI_SASS_PLAIN('\uebcb'),
        DEVI_SYMFONY_PLAIN_WORDMARK('\ue602'),
        DEVI_SYMFONY_PLAIN('\ue605'),
        DEVI_TRAVIS_PLAIN_WORDMARK('\uebcc'),
        DEVI_TRAVIS_PLAIN('\uebcd'),
        DEVI_TRELLO_PLAIN_WORDMARK('\uebce'),
        DEVI_TRELLO_PLAIN('\uebcf'),
        DEVI_UBUNTU_PLAIN_WORDMARK('\uebd0'),
        DEVI_UBUNTU_PLAIN('\uebd1'),
        DEVI_VIM_PLAIN('\uebf3'),
        DEVI_WINDOWS8_PLAIN_WORDMARK('\uebf4'),
        DEVI_WINDOWS8_PLAIN('\uebf5'),
        DEVI_WORDPRESS_PLAIN_WORDMARK('\uebfd'),
        DEVI_WORDPRESS_PLAIN('\uebfe'),
        DEVI_YII_PLAIN_WORDMARK('\uec01'),
        DEVI_YII_PLAIN('\uec02'),
        DEVI_ZEND_PLAIN_WORDMARK('\uec03'),
        DEVI_ZEND_PLAIN('\uec04');

        char character;

        Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public char getCharacter() {
            return character;
        }

        public String getName() {
            return name();
        }

        // remember the typeface so we can use it later
        private static ITypeface typeface;

        /** Gets the ITypeface corresponding to this IIcon.
         *
         * @return ITypeface object corresponding to this IIcon.
         */
        public ITypeface getTypeface() {
            if (typeface == null) {
                setTypeface(new Devicon());
            }
            return typeface;
        }

        private static void setTypeface(Devicon typeface) {
            Icon.typeface = typeface;
        }
    }
}
