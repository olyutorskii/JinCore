[UTF-8 Japanese]

                              JinCoreライブラリ
                                  README

                                              Copyright(c) 2009 olyutorskii


=== JinCoreとは ===


 JinCoreライブラリは、Jindolf プロジェクトのコア共通部分を構成する
Javaパッケージです。
 このアーカイブは、JinCoreライブラリの開発資産を、ある時点で凍結したものです。

 Jindolfは、CGIゲーム「人狼BBS」の専用クライアント開発プロジェクトです。
 JinCoreは、Jindolf以外の人狼BBSクライアント製作者向けに、
Jindolfの機能の一部を提供することを目的に発足した、派生プロジェクトです。

※ このアーカイブにはJindolfの実行バイナリは含まれていません。
　 Jindolfを動かしたい方は、jindolfで始まり拡張子が*.jarであるファイルを
　 別途入手してください。
※ 人狼BBSのURLは [ http://homepage2.nifty.com/ninjinia/ ] まで
※ 人狼BBSを主催するninjin氏は、JinCoreの製作に一切関与していません。
　 JinCoreに関する問い合わせををninjin氏へ投げかけないように！約束だよ！


=== ソースコードに関して ===

 - JinCoreはJava言語(JLS3)で記述されたプログラムです。
 - JinCoreは他のプログラムに組み込まれて利用されるライブラリです。
   JARファイルによるライブラリ提供や、他プロジェクトのソースツリーへの
   マージの形で利用される事を想定しています。
 - JinCoreはJRE1.5に準拠したJava実行環境で利用できるように作られています。
   原則として、JRE1.5に準拠した実行系であれば、プラットフォームを選びません。


=== アーカイブ管理体制 ===

  このアーカイブは、UTF-8による開発環境を前提として構成されています。
  このアーカイブの原本となる開発資産は、
      http://svn.sourceforge.jp/svnroot/jindolf/JinCore/
  を上位に持つSubversionリポジトリで管理されています。
  アーカイブの代わりにSubversionを通じて開発資産にアクセスすることにより、
  任意の文字コードに変換されたJavaソースファイルや各種リソースを
  容易に入手することが可能です。


=== 開発プロジェクト運営元 ===

  http://sourceforge.jp/projects/jindolf/ まで。


=== ディレクトリ内訳構成 ===

基本的にはMaven2のmaven-archetype-quickstart構成に準じます。

./README.txt
    あなたが今見てるこれ。

./CHANGELOG.txt
    変更履歴。

./LICENSE.txt
    ライセンスに関して。

./pom.xml
    Maven2用プロジェクト構成定義ファイル。

./build.xml
    Ant用追加タスク。

./src/main/java/
    Javaのソースコード。

./src/main/resources/
    プロパティファイルなどの各種リソース。

./src/test/java/
    JUnit 4.* 用のユニットテストコード。

./src/main/config/checks.xml
    Checkstyle用configファイル。

./src/main/config/pmdrules.xml
    PMD用ルール定義ファイル。

./src/main/assembly/descriptor.xml
    ソースアーカイブ構成定義ファイル。


--- EOF ---
