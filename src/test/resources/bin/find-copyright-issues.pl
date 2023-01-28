#!/usr/bin/env perl

# Copyright 2023 Green Filing, LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

use strict;
use File::Find;
use FindBin qw($Bin);

my $findsrc = "$Bin/../../..";

my $fileToYearMap = getFileVcsModTime($findsrc);

find(\&wanted, $findsrc);

exit;

sub wanted {
	return 0 if ($File::Find::name !~ /\.(java|pl)/);
	return 0 if ($File::Find::dir =~ m|$findsrc/target|);

	my $relativeFile = $File::Find::name;
	$relativeFile =~ s|$findsrc/||;

	# we can't enforce this because some files might not be in version control yet
	# if (!exists($fileToYearMap->{$relativeFile})) {
	# 	die "Unable to find $relativeFile in fileToYearMap\n";
	# }

	# The copyright notice must appear in the first 10 lines of the file
	my $header = '';
	open(I, "<$File::Find::name") || die "Couldn't open $File::Find::name: $!\n";
	foreach my $i (1..10) {
		$header .= <I>;
	}
	close(I);
	if ($header !~ / Copyright \d+(-\d+)? Green Filing, LLC/ || $header !~ /Licensed under the Apache License/) {
		print STDERR "missing/corrupt copyright in $relativeFile\n";
	}

	if (exists($fileToYearMap->{$relativeFile}) && $header =~ / Copyright (?:\d+-)?(\d\d\d\d) Green Filing, LLC/) {
		my $fileYear = $1;
		if ($fileYear ne $fileToYearMap->{$relativeFile}) {
			print STDERR "last copyright year not accurate (file: $fileYear, vcs: $fileToYearMap->{$relativeFile}) in $relativeFile\n";
		}
	}
}

# pieced together from multiple answers from https://serverfault.com/questions/401437/how-to-retrieve-the-last-modification-date-of-all-files-in-a-git-repository
# git ls-files '*.java' '*.pl' -z | TZ=UTC xargs -0n1 -I_ git --no-pager log -1 --date=format:%Y --format="%ad _" -- _
sub getFileVcsModTime {
	my $base = shift;
	my $fileToYear = {};

	my @filesInGit = ();
	open(P, "git -C $base ls-files '*.java' '*.pl' |") || die "Couldn't get files from git: $!\n";
	while(my $file = <P>) {
		chomp($file);
		push(@filesInGit, $file);
	}
	close(P);

	foreach my $file (sort @filesInGit) {
		open(P, "git -C $base  --no-pager log -1 --date=format:%Y --format=%ad -- $file |") || die "Couldn't get year for $file from git: $!\n";
		while (my $year = <P>) {
			chomp($year);
			$fileToYear->{$file} = $year;
		}
		close(P);
	}

	# use Data::Dumper;
	# print Dumper($fileToYear);

	return $fileToYear;
}
